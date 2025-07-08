package com.ee.kmp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ee.kmp.data.Breed
import com.ee.kmp.ui.views.BreedDetail
import com.ee.kmp.ui.views.BreedList

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Routes.BreedsList.path) {
        composable(Routes.BreedsList.path) {
            BreedList() {
                navController.navigate(Routes.BreedDetail.path)
            }
        }
        composable(Routes.BreedDetail.path) {
            BreedDetail(Breed())
        }

    }
}