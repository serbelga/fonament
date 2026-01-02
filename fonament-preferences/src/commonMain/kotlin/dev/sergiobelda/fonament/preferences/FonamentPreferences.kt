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

class FonamentPreferences(
    private val dataStore: DataStore<Preferences>,
) {
    suspend operator fun set(
        key: String,
        value: Int,
    ) {
        set(intPreferencesKey(key), value)
    }

    suspend operator fun set(
        key: String,
        value: Double,
    ) {
        set(doublePreferencesKey(key), value)
    }

    suspend operator fun set(
        key: String,
        value: String,
    ) {
        set(stringPreferencesKey(key), value)
    }

    suspend operator fun set(
        key: String,
        value: Boolean,
    ) {
        set(booleanPreferencesKey(key), value)
    }

    suspend operator fun set(
        key: String,
        value: Float,
    ) {
        set(floatPreferencesKey(key), value)
    }

    suspend operator fun set(
        key: String,
        value: Long,
    ) {
        set(longPreferencesKey(key), value)
    }

    suspend operator fun set(
        key: String,
        value: Set<String>,
    ) {
        set(stringSetPreferencesKey(key), value)
    }

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

    fun getInt(key: String): Flow<Int?> =
        get(intPreferencesKey(key))

    fun getIntOrDefault(
        key: String,
        default: Int,
    ): Flow<Int> =
        getOrDefault(intPreferencesKey(key), default)

    fun getDouble(key: String): Flow<Double?> =
        get(doublePreferencesKey(key))

    fun getDoubleOrDefault(
        key: String,
        default: Double,
    ): Flow<Double> =
        getOrDefault(doublePreferencesKey(key), default)

    fun getString(key: String): Flow<String?> =
        get(stringPreferencesKey(key))

    fun getStringOrDefault(
        key: String,
        default: String,
    ): Flow<String> =
        getOrDefault(stringPreferencesKey(key), default)

    fun getBoolean(key: String): Flow<Boolean?> =
        get(booleanPreferencesKey(key))

    fun getBooleanOrDefault(
        key: String,
        default: Boolean,
    ): Flow<Boolean> =
        getOrDefault(booleanPreferencesKey(key), default)

    fun getFloat(key: String): Flow<Float?> =
        get(floatPreferencesKey(key))

    fun getFloatOrDefault(
        key: String,
        default: Float,
    ): Flow<Float> =
        getOrDefault(floatPreferencesKey(key), default)

    fun getLong(key: String): Flow<Long?> =
        get(longPreferencesKey(key))

    fun getLongOrDefault(
        key: String,
        default: Long,
    ): Flow<Long> =
        getOrDefault(longPreferencesKey(key), default)

    fun getStringSet(key: String): Flow<Set<String>?> =
        get(stringSetPreferencesKey(key))

    fun getStringSetOrDefault(
        key: String,
        default: Set<String>,
    ): Flow<Set<String>> =
        getOrDefault(stringSetPreferencesKey(key), default)

    fun getByteArray(key: String): Flow<ByteArray?> =
        get(byteArrayPreferencesKey(key))

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
