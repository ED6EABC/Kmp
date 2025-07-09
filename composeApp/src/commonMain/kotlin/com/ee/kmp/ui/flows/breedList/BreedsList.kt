package com.ee.kmp.ui.flows.breedList

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ee.kmp.data.model.Breed
import com.ee.kmp.di.dataModule
import com.ee.kmp.di.domineModule
import com.ee.kmp.di.platformDataModule
import com.ee.kmp.di.presentationModule
import com.ee.kmp.ui.actions.SystemAction
import com.ee.kmp.ui.composables.BreedCard
import com.ee.kmp.ui.composables.CustomTopBar
import com.ee.kmp.ui.composables.Loader
import com.ee.kmp.ui.composables.TopBarConfiguration
import com.ee.kmp.ui.flows.login.BreedAction
import com.ee.kmp.ui.navigation.Routes
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplicationPreview
import org.koin.compose.viewmodel.koinViewModel

@Preview
@Composable
private fun BreedListPreview() {
    KoinApplicationPreview(
        application = {
            modules(
                platformDataModule,

                dataModule,
                domineModule,
                presentationModule
            )
        }
    ) {
        BreedList(
            breedViewModel = koinViewModel(),
            onSystemAction = {}
        )
    }
}

@Composable
fun BreedList(
    breedViewModel: BreedViewModel,
    onSystemAction: (SystemAction) -> Unit,
) {

    val uiState by breedViewModel.uiState.collectAsState()
    val lazyListState = rememberLazyListState()

    val reachedBottom: Boolean by remember {
        derivedStateOf {
            val lastVisibleItem = lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem != null && lastVisibleItem.index == uiState.breeds?.size?.minus(1)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomTopBar(
                config = TopBarConfiguration(showBack = false, showFavorites = true),
                onAction = { onSystemAction(SystemAction.Navigate(Routes.Favorites)) }
            )
        },
         content = { paddingValues ->

             LazyColumn(
                 modifier = Modifier.padding(paddingValues),
                 state = lazyListState,
                 contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp)
             ) {
                 items(
                     items = uiState.breeds as List<Breed?>,
                     key = { it?.id ?: "" }
                 ) {
                     it?.let { breed ->
                         BreedCard(
                             breed = it,
                             onClick = {
                                 breedViewModel.onAction(BreedAction.OnBreedSelected(it, onSystemAction))
                             }
                         )
                     }
                 }
             }

             if(uiState.isError) {
                 TODO()
             }

             Loader(uiState.isLoading)
         }
    )

    LaunchedEffect(reachedBottom) {
        if (reachedBottom && !uiState.isLoading && !uiState.isReachLimit) {
            breedViewModel.onAction(BreedAction.OnLoadBreeds)
        }
    }
}