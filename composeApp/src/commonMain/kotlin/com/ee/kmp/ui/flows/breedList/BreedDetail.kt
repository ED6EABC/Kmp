package com.ee.kmp.ui.flows.breedList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun BreedDetail(breedViewModel: BreedViewModel) {

    //val breedViewModel:BreedViewModel = koinViewModel()
    val breed by breedViewModel.breedSelected.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        breed?.name?.let { Text(it) }
    }
}