/*
 * Copyright 2026 Sergio Belda
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.sergiobelda.fonament.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import co.touchlab.stately.collections.ConcurrentMutableMap
import co.touchlab.stately.concurrency.Lock
import co.touchlab.stately.concurrency.withLock
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

actual class FonamentPreferencesDataStoreSingleton {
    companion object {
        private val lock = Lock()

        private val cache: ConcurrentMutableMap<String, DataStore<Preferences>> =
            ConcurrentMutableMap()
    }

    @OptIn(ExperimentalForeignApi::class)
    private val documentDirectory: NSURL? =
        NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )

    actual operator fun get(name: String): DataStore<Preferences> =
        lock.withLock {
            cache[name] ?: run {
                val instance =
                    createDataStore(
                        producePath = {
                            documentDirectory?.path.orEmpty() +
                                "/${name.toPreferencesDataStoreFileName()}"
                        },
                    )
                cache[name] = instance
                instance
            }
        }
}
