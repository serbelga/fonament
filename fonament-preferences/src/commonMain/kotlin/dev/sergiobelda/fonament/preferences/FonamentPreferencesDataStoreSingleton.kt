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
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath

/**
 * A class that creates a [DataStore] instance for a given file name as a singleton.
 */
expect class FonamentPreferencesDataStoreSingleton {
    internal val dataStoreFilePath: DataStoreFilePath

    /**
     * Create a [DataStore] instance for the given [name] or return the existing one.
     */
    operator fun get(name: String): DataStore<Preferences>
}

/**
 * Creates an instance of [DataStore] given its [name].
 */
internal fun FonamentPreferencesDataStoreSingleton.createDataStore(
    name: String,
): DataStore<Preferences> =
    PreferenceDataStoreFactory.createWithPath(
        produceFile = { dataStoreFilePath.path(name).toPath() },
    )
