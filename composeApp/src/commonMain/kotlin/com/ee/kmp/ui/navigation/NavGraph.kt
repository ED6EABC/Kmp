package com.ee.kmp.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ee.kmp.data.Breed
import com.ee.kmp.ui.views.BreedDetail
import com.ee.kmp.ui.views.BreedList
import com.ee.kmp.ui.views.Login

@Composable
fun NavGraph(
    padding: PaddingValues,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Login.path,
        modifier = Modifier.padding(padding)
    ) {
        composable(Routes.Login.path) {
            Login()
        }
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