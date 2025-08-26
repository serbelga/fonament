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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.sergiobelda.fonament.demos.navigation.details.DetailsScreen
import dev.sergiobelda.fonament.demos.navigation.home.HomeScreen
import dev.sergiobelda.fonament.demos.navigation.home.HomeViewModel
import dev.sergiobelda.fonament.demos.navigation.home.homeNavigationEventHandler
import dev.sergiobelda.fonament.navigation.ContentNavigationNode
import dev.sergiobelda.fonament.ui.FonamentUIState

class NavigationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = HomeScreen,
            ) {
                composable<HomeScreen> {
                    HomeScreen.ContentNavigationNode(
                        navigationEventHandler = homeNavigationEventHandler(
                            navigateToDetails = {
                                navController.navigate(DetailsScreen)
                            },
                        ),
                        viewModel = viewModel<HomeViewModel>(),
                    )
                }
                composable<DetailsScreen> {
                    DetailsScreen.Content(
                        uiState = FonamentUIState,
                    )
                }
            }
        }
    }
}
