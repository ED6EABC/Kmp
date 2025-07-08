package com.ee.kmp.ui.flows.breedList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import kmp.composeapp.generated.resources.Res
import kmp.composeapp.generated.resources.error_svgrepo_com
import kmp.composeapp.generated.resources.heart_straight_fill_svgrepo_com
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.vectorResource

@Composable
fun BreedDetail(breedViewModel: BreedViewModel) {

    val breed by breedViewModel.breedSelected.collectAsState()

    val painter = rememberAsyncImagePainter("https://cdn2.thecatapi.com/images/${breed?.referenceImageId}.jpg")
    val state by painter.state.collectAsState()

    Column {
        when (state) {
            is AsyncImagePainter.State.Error -> {
                Image(
                    painterResource(Res.drawable.error_svgrepo_com),
                    contentDescription = "Error loading image",
                    modifier = Modifier
                        .weight(0.8f)
                        .aspectRatio(16f / 9f)
                )
            }
            is AsyncImagePainter.State.Success -> {
                Image(
                    painter = painter,
                    contentDescription = breed?.name,
                    modifier = Modifier
                        .weight(0.8f)
                        .aspectRatio(16f / 9f),
                    contentScale = ContentScale.Crop
                )
            }
            else -> {
                CircularProgressIndicator( modifier = Modifier.size(150.dp))
            }
        }

        breed?.description?.let { Text(it) }
        breed?.origin?.let { Text(it) }
        breed?.temperament?.let { Text(it) }
        breed?.lifeSpan?.let { Text(it) }

        IconButton(
            onClick = {

            }
        ) {
            Icon(imageVector = vectorResource(Res.drawable.heart_straight_fill_svgrepo_com), "Favorite")
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        breed?.name?.let { Text(it) }
    }
}