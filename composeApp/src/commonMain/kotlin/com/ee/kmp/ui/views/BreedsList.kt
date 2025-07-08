package com.ee.kmp.ui.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ee.kmp.data.Breed
import com.ee.kmp.ui.composables.BreedCard
import com.ee.kmp.ui.viewModels.MainViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BreedList(
    viewModel: MainViewModel = koinViewModel(),
    onClick: (Breed) -> Unit = {}
) {
    val breeds: List<Breed>? by viewModel.state.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp)
    ) {
        items(
            items = breeds as List<Breed?>,
            key = { it?.id ?: "" }
        ) {
            it?.let { BreedCard(it, onClick) }
        }
    }
}