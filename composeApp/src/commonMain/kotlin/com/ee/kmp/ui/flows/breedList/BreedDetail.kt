package com.ee.kmp.ui.flows.breedList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import com.ee.kmp.ui.actions.SystemAction
import com.ee.kmp.ui.composables.CustomTopBar
import com.ee.kmp.ui.composables.TopBarConfiguration
import com.ee.kmp.ui.flows.login.BreedAction
import com.ee.kmp.ui.navigation.Routes
import kmp.composeapp.generated.resources.Res
import kmp.composeapp.generated.resources.error_svgrepo_com
import kmp.composeapp.generated.resources.heart_like_favorite_svgrepo_com
import kmp.composeapp.generated.resources.heart_straight_fill_svgrepo_com
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.vectorResource

@Composable
fun BreedDetail(
    breedViewModel: BreedViewModel,
    onSystemAction: (SystemAction) -> Unit
) {

    val breedDetail by breedViewModel.breedSelected.collectAsState()

    val painter = rememberAsyncImagePainter("https://cdn2.thecatapi.com/images/${breedDetail?.breed?.referenceImageId}.jpg")
    val state by painter.state.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomTopBar(
                config = TopBarConfiguration(title = breedDetail?.breed?.name, showBack = true, showFavorites = true),
                onBack = { onSystemAction(SystemAction.NavigateBack) },
                onAction = { onSystemAction(SystemAction.Navigate(Routes.Favorites)) }
            )
        },
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
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
                            contentDescription = breedDetail?.breed?.name,
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

                with(breedDetail?.breed) {
                    this?.description?.let { Text(it) }
                    this?.origin?.let { Text(it) }
                    this?.temperament?.let { Text(it) }
                    this?.lifeSpan?.let { Text(it) }
                }

                IconButton(
                    onClick = {
                        breedDetail?.let { breedViewModel.onAction(BreedAction.OnSaveAsFavorite(it)) }
                    }
                ) {
                    val icon = if(breedDetail?.isFavorite ?: false)
                        Res.drawable.heart_straight_fill_svgrepo_com
                    else
                        Res.drawable.heart_like_favorite_svgrepo_com

                    Icon(imageVector = vectorResource(icon), "Favorite")
                }
            }
        }
    )
}