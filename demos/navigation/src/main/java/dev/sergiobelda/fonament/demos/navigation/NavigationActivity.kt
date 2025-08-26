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

package dev.sergiobelda.fonament.demos.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.sergiobelda.fonament.demos.navigation.details.DetailsContent
import dev.sergiobelda.fonament.demos.navigation.details.detailsNavigationEventHandler
import dev.sergiobelda.fonament.demos.navigation.home.HomeContent
import dev.sergiobelda.fonament.demos.navigation.home.homeNavigationEventHandler
import dev.sergiobelda.fonament.navigation.ContentNavigationNode

class NavigationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = HomeContent,
            ) {
                composable<HomeContent> {
                    HomeContent.ContentNavigationNode(
                        navigationEventHandler = homeNavigationEventHandler(
                            navigateToDetails = {
                                navController.navigate(DetailsContent)
                            },
                        )
                    )
                }
                composable<DetailsContent> {
                    DetailsContent.ContentNavigationNode(
                        navigationEventHandler = detailsNavigationEventHandler(
                            navigateBack = {
                                navController.popBackStack()
                            }
                        )
                    )
                }
            }
        }
    }
}
