package com.ee.kmp.ui.flows.favorites

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ee.kmp.data.Breed
import com.ee.kmp.ui.actions.SystemAction
import com.ee.kmp.ui.composables.BreedCard
import com.ee.kmp.ui.composables.BreedCardType
import com.ee.kmp.ui.composables.CustomTopBar
import com.ee.kmp.ui.composables.TopBarConfiguration
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun AppPreview() {
    Favorites(null) {}
}

@Composable
fun Favorites(breeds: List<Breed>?, onSystemAction: (SystemAction) -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomTopBar(
                config = TopBarConfiguration(showBack = true),
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
                        BreedCard(BreedCardType.BreedAsFavorite, it) {
                            //TODO delete
                        }
                    }
                }
            }
        }
    )
}