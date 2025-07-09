package com.ee.kmp.ui.flows.favorites

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.ee.kmp.data.model.Breed
import com.ee.kmp.ui.actions.SystemAction
import com.ee.kmp.ui.composables.BreedCard
import com.ee.kmp.ui.composables.BreedCardType
import com.ee.kmp.ui.composables.CustomTopBar
import com.ee.kmp.ui.composables.TopBarConfiguration
import kmp.composeapp.generated.resources.Res
import kmp.composeapp.generated.resources.favorites
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Preview
@Composable
private fun AppPreview() {
    Favorites() {}
}

@Composable
fun Favorites(onSystemAction: (SystemAction) -> Unit) {

    val viewModel = koinViewModel<FavoritesViewModel>()
    val breeds by viewModel.state.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomTopBar(
                config = TopBarConfiguration(title = stringResource(Res.string.favorites), showBack = true),
                onBack = { onSystemAction(SystemAction.NavigateBack) },
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier.padding(paddingValues)
            ) {
                items(
                    items = breeds as List<Breed?>,
                    key = { it?.id ?: "" }
                ) {
                    it?.let {
                        BreedCard(
                            breedCardType = BreedCardType.BreedAsFavorite,
                            breed = it,
                            onDelete = { id ->
                                viewModel.onAction(FavoritesAction.RemoveFavorite(id))
                            }
                        )
                    }
                }
            }
        }
    )
}