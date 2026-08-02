/*
 * Copyright 2025 Sergio Belda
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
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.byteArrayPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * A class that provides functions to set and get data from a [DataStore]<[Preferences]> instance.
 */
class FonamentPreferences(
    private val dataStore: DataStore<Preferences>,
) {
    /**
     * Sets an [Int] [value] in the data store preferences.
     */
    suspend operator fun set(
        key: String,
        value: Int,
    ) {
        set(intPreferencesKey(key), value)
    }

    /**
     * Sets a [Double] [value] in the data store preferences.
     */
    suspend operator fun set(
        key: String,
        value: Double,
    ) {
        set(doublePreferencesKey(key), value)
    }

    /**
     * Sets a [String] [value] in the data store preferences.
     */
    suspend operator fun set(
        key: String,
        value: String,
    ) {
        set(stringPreferencesKey(key), value)
    }

    /**
     * Sets a [Boolean] [value] in the data store preferences.
     */
    suspend operator fun set(
        key: String,
        value: Boolean,
    ) {
        set(booleanPreferencesKey(key), value)
    }

    /**
     * Sets a [Float] [value] in the data store preferences.
     */
    suspend operator fun set(
        key: String,
        value: Float,
    ) {
        set(floatPreferencesKey(key), value)
    }

    /**
     * Sets a [Long] [value] in the data store preferences.
     */
    suspend operator fun set(
        key: String,
        value: Long,
    ) {
        set(longPreferencesKey(key), value)
    }

    /**
     * Sets a [Set]<[String]> [value] in the data store preferences.
     */
    suspend operator fun set(
        key: String,
        value: Set<String>,
    ) {
        set(stringSetPreferencesKey(key), value)
    }

    /**
     * Sets a [ByteArray] [value] in the data store preferences.
     */
    suspend operator fun set(
        key: String,
        value: ByteArray,
    ) {
        set(byteArrayPreferencesKey(key), value)
    }

    private suspend inline fun <T> set(
        key: Preferences.Key<T>,
        value: T,
    ) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    /**
     * Retrieves a [Flow] containing the value from the data store preferences given its [key],
     * or null otherwise.
     */
    fun getInt(key: String): Flow<Int?> =
        get(intPreferencesKey(key))

    /**
     * Retrieves a [Flow] containing the value from the data store preferences given its [key],
     * or [default] otherwise.
     */
    fun getIntOrDefault(
        key: String,
        default: Int,
    ): Flow<Int> =
        getOrDefault(intPreferencesKey(key), default)

    /**
     * Retrieves a [Flow] containing the value from the data store preferences given its [key],
     * or null otherwise.
     */
    fun getDouble(key: String): Flow<Double?> =
        get(doublePreferencesKey(key))

    /**
     * Retrieves a [Flow] containing the value from the data store preferences given its [key],
     * or [default] otherwise.
     */
    fun getDoubleOrDefault(
        key: String,
        default: Double,
    ): Flow<Double> =
        getOrDefault(doublePreferencesKey(key), default)

    /**
     * Retrieves a [Flow] containing the value from the data store preferences given its [key],
     * or null otherwise.
     */
    fun getString(key: String): Flow<String?> =
        get(stringPreferencesKey(key))

    /**
     * Retrieves a [Flow] containing the value from the data store preferences given its [key],
     * or [default] otherwise.
     */
    fun getStringOrDefault(
        key: String,
        default: String,
    ): Flow<String> =
        getOrDefault(stringPreferencesKey(key), default)

    /**
     * Retrieves a [Flow] containing the value from the data store preferences given its [key],
     * or null otherwise.
     */
    fun getBoolean(key: String): Flow<Boolean?> =
        get(booleanPreferencesKey(key))

    /**
     * Retrieves a [Flow] containing the value from the data store preferences given its [key],
     * or [default] otherwise.
     */
    fun getBooleanOrDefault(
        key: String,
        default: Boolean,
    ): Flow<Boolean> =
        getOrDefault(booleanPreferencesKey(key), default)

    /**
     * Retrieves a [Flow] containing the value from the data store preferences given its [key],
     * or null otherwise.
     */
    fun getFloat(key: String): Flow<Float?> =
        get(floatPreferencesKey(key))

    /**
     * Retrieves a [Flow] containing the value from the data store preferences given its [key],
     * or [default] otherwise.
     */
    fun getFloatOrDefault(
        key: String,
        default: Float,
    ): Flow<Float> =
        getOrDefault(floatPreferencesKey(key), default)

    /**
     * Retrieves a [Flow] containing the value from the data store preferences given its [key],
     * or null otherwise.
     */
    fun getLong(key: String): Flow<Long?> =
        get(longPreferencesKey(key))

    /**
     * Retrieves a [Flow] containing the value from the data store preferences given its [key],
     * or [default] otherwise.
     */
    fun getLongOrDefault(
        key: String,
        default: Long,
    ): Flow<Long> =
        getOrDefault(longPreferencesKey(key), default)

    /**
     * Retrieves a [Flow] containing the value from the data store preferences given its [key],
     * or null otherwise.
     */
    fun getStringSet(key: String): Flow<Set<String>?> =
        get(stringSetPreferencesKey(key))

    /**
     * Retrieves a [Flow] containing the value from the data store preferences given its [key],
     * or [default] otherwise.
     */
    fun getStringSetOrDefault(
        key: String,
        default: Set<String>,
    ): Flow<Set<String>> =
        getOrDefault(stringSetPreferencesKey(key), default)

    /**
     * Retrieves a [Flow] containing the value from the data store preferences given its [key],
     * or null otherwise.
     */
    fun getByteArray(key: String): Flow<ByteArray?> =
        get(byteArrayPreferencesKey(key))

    /**
     * Retrieves a [Flow] containing the value from the data store preferences given its [key],
     * or [default] otherwise.
     */
    fun getByteArrayOrDefault(
        key: String,
        default: ByteArray,
    ): Flow<ByteArray> =
        getOrDefault(byteArrayPreferencesKey(key), default)

    private fun <T> get(key: Preferences.Key<T>): Flow<T?> =
        dataStore.data.map { preferences ->
            preferences[key]
        }

    private fun <T> getOrDefault(
        key: Preferences.Key<T>,
        default: T,
    ): Flow<T> =
        dataStore.data.map { preferences ->
            preferences[key] ?: default
        }
}
