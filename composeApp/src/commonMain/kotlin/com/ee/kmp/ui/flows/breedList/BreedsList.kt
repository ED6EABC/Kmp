package com.ee.kmp.ui.flows.breedList

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ee.kmp.data.model.Breed
import com.ee.kmp.ui.actions.SystemAction
import com.ee.kmp.ui.composables.BreedCard
import com.ee.kmp.ui.composables.CustomTopBar
import com.ee.kmp.ui.composables.Loader
import com.ee.kmp.ui.composables.TopBarConfiguration
import com.ee.kmp.ui.flows.breedList.model.BreedAction
import com.ee.kmp.ui.navigation.Routes
import kmp.composeapp.generated.resources.Res
import kmp.composeapp.generated.resources.requestAction
import kmp.composeapp.generated.resources.requestError
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun BreedListPreview() {
    BreedList(
        uiState = UiState(
            breeds = mutableListOf(),
            isLoading = false,
            page = 0,
            error = NetworkError(),
            isReachLimit = false
        ),
        onSystemAction = {},
        onAction = {}
    )
}

@Composable
fun BreedList(
    uiState: UiState,
    onSystemAction: (SystemAction) -> Unit,
    onAction: (BreedAction) -> Unit
) {

    val lazyListState = rememberLazyListState()
    val snackBarHostState = remember { SnackbarHostState() }
    val snackBarLabel = stringResource(Res.string.requestError)
    val snackBarActionLabel = stringResource(Res.string.requestAction)

    val reachedBottom: Boolean by remember {
        derivedStateOf {
            val lastVisibleItem = lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem != null && lastVisibleItem.index == uiState.breeds.size.minus(1)
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
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
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
                            onClick = { onAction(BreedAction.OnBreedSelected(it, onSystemAction)) }
                        )
                    }
                }
            }
            Loader(uiState.isLoading)
        }
    )

    LaunchedEffect(uiState.error.isError) {
        if(uiState.error.isError) {
            val snackBarResult = snackBarHostState.showSnackbar(
                message = snackBarLabel,
                actionLabel = snackBarActionLabel,
                withDismissAction = false,
                duration = androidx.compose.material3.SnackbarDuration.Indefinite
            )

            if(snackBarResult == SnackbarResult.ActionPerformed) {
                onAction(BreedAction.OnLoadBreeds)
            }
        }
    }

    LaunchedEffect(reachedBottom) {
        if (reachedBottom && !uiState.isLoading && !uiState.isReachLimit) {
            onAction(BreedAction.OnLoadBreeds)
        }
    }

}