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

import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class FonamentPreferencesTest : PlatformFonamentPreferencesTest() {
    val fonamentPreferences: FonamentPreferences =
        factory.create(
            TEST_PREFERENCES_NAME,
        )

    @BeforeTest
    fun before() {
        setUp()
    }

    @AfterTest
    fun after() {
        clearPreferences(TEST_PREFERENCES_NAME.toPreferencesDataStoreFileName())
    }

    @Test
    fun `test set and get Int`() =
        runTest {
            fonamentPreferences["test_int"] = 123
            val result = fonamentPreferences.getInt("test_int").firstOrNull()
            assertEquals(123, result)
        }

    @Test
    fun `test set and get Double`() =
        runTest {
            fonamentPreferences["test_double"] = 123.4
            val result = fonamentPreferences.getDouble("test_double").firstOrNull()
            assertEquals(123.4, result)
        }

    @Test
    fun `test set and get String`() =
        runTest {
            fonamentPreferences["test_string"] = "test"
            val result = fonamentPreferences.getString("test_string").firstOrNull()
            assertEquals("test", result)
        }

    @Test
    fun `test set and get Boolean`() =
        runTest {
            fonamentPreferences["test_boolean"] = true
            val result = fonamentPreferences.getBoolean("test_boolean").firstOrNull()
            assertEquals(true, result)
        }

    @Test
    fun `test set and get Float`() =
        runTest {
            fonamentPreferences["test_float"] = 456.7f
            val result = fonamentPreferences.getFloat("test_float").firstOrNull()
            assertEquals(456.7f, result)
        }

    @Test
    fun `test set and get Long`() =
        runTest {
            fonamentPreferences["test_long"] = 78L
            val result = fonamentPreferences.getLong("test_long").firstOrNull()
            assertEquals(78L, result)
        }

    @Test
    fun `test set and get StringSet`() =
        runTest {
            fonamentPreferences["test_string_set"] = setOf("test", "test", "a")
            val result = fonamentPreferences.getStringSet("test_string_set").firstOrNull()
            assertEquals(setOf("test", "a"), result)
        }
}

private const val TEST_PREFERENCES_NAME = "test"
