package com.ee.kmp.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ee.kmp.ui.actions.SystemAction
import com.ee.kmp.ui.flows.breedList.BreedDetail
import com.ee.kmp.ui.flows.breedList.BreedList
import com.ee.kmp.ui.flows.breedList.BreedViewModel
import com.ee.kmp.ui.flows.login.Login
import org.koin.compose.viewmodel.koinViewModel
import org.koin.compose.viewmodel.sharedKoinViewModel

@Composable
fun NavGraph(
    padding: PaddingValues,
    navController: NavHostController,
    onSystemAction: (SystemAction) -> Unit,
) {

    NavHost(
        navController = navController,
        startDestination = Routes.Login.path,
        modifier = Modifier.padding(padding)
    ) {
        composable(Routes.Login.path) {
            Login(onSystemAction)
        }
        composable(Routes.BreedsList.path) {

            val parentEntry: NavBackStackEntry = remember(navController.currentBackStackEntry) {
                navController.getBackStackEntry(Routes.BreedsList.path)
            }

            BreedList(koinViewModel(viewModelStoreOwner = parentEntry)) {
                navController.navigate(Routes.BreedDetail.path)
            }
        }
        composable(Routes.BreedDetail.path) {

            val parentEntry: NavBackStackEntry = remember(navController.currentBackStackEntry) {
                navController.getBackStackEntry(Routes.BreedsList.path)
            }

            BreedDetail(koinViewModel(viewModelStoreOwner = parentEntry))
        }

    }
}