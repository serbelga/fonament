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

/**
 * Class that represent the path to a preferences DataStore.
 */
internal expect class DataStoreFilePath {
    /**
     * Returns the platform-specific path to a preferences DataStore.
     */
    internal val platformPath: String
}

private val DataStoreFilePath.directory: String get() =
    "$platformPath/datastore"

/**
 * Returns a string representation of a given [String] as a file name for a preferences DataStore.
 */
private fun String.dataStoreFileName(): String =
    "$this.preferences_pb"

internal fun DataStoreFilePath.path(name: String): String =
    directory + "/" + name.dataStoreFileName()
