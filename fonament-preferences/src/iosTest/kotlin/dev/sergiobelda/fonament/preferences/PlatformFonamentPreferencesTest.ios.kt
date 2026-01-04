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

import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSFileManager

actual open class PlatformFonamentPreferencesTest actual constructor() {
    internal actual val fonamentPreferencesFactory: FonamentPreferencesFactory =
        FonamentPreferencesFactory()

    internal actual val dataStoreFilePath: DataStoreFilePath = DataStoreFilePath()

    @OptIn(ExperimentalForeignApi::class)
    actual fun clearPreferences() {
        val manager = NSFileManager.defaultManager
        manager.removeItemAtPath(dataStoreFilePath.path(TEST_PREFERENCES_NAME), null)
    }
}
