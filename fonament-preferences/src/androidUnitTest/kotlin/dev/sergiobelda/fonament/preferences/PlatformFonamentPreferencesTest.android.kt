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

import android.content.Context
import io.mockk.every
import io.mockk.mockk
import java.io.File

actual open class PlatformFonamentPreferencesTest {
    val context: Context = mockk<Context>(relaxed = true)

    actual var factory: FonamentPreferencesFactory = FonamentPreferencesFactory(context)

    actual fun setUp() {
        every { context.filesDir } returns File("build/filesDir/")
    }

    actual fun clearPreferences(fileName: String) {
        val file = context.filesDir.resolve(fileName)
        if (file.exists()) {
            file.delete()
        }
    }
}
