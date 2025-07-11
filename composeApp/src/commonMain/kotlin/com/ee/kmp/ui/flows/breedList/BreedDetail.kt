package com.ee.kmp.ui.flows.breedList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import com.ee.kmp.data.model.Breed
import com.ee.kmp.ui.actions.SystemAction
import com.ee.kmp.ui.colorBlue
import com.ee.kmp.ui.colorRed
import com.ee.kmp.ui.composables.CustomTopBar
import com.ee.kmp.ui.composables.TopBarConfiguration
import com.ee.kmp.ui.flows.breedList.model.BreedAction
import com.ee.kmp.ui.navigation.Routes
import kmp.composeapp.generated.resources.Res
import kmp.composeapp.generated.resources.error_svgrepo_com
import kmp.composeapp.generated.resources.heart_straight_fill_svgrepo_com
import kmp.composeapp.generated.resources.lifeSpan
import kmp.composeapp.generated.resources.origin
import kmp.composeapp.generated.resources.temperament
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun BreedDetailPreview() {
    BreedDetail(
        breedDetail = BreedDetail(
            breed = Breed(
                id = "",
                referenceImageId = "hBXicehMA",
                name = "Bengal",
                description = "Bengals are known for their striking appearance, resembling a miniature leopard with their distinctive spots and rosettes. They are highly energetic, playful, and intelligent cats, requiring plenty of mental and physical stimulation. Bengals are also quite vocal and enjoy interacting with their human companions."

            ),
            isFavorite = true
        ),
        onSystemAction = {}
    ) {}
}

@Composable
fun BreedDetail(
    breedDetail: BreedDetail,
    onSystemAction: (SystemAction) -> Unit,
    onAction: (BreedAction) -> Unit
) {

    val painter = rememberAsyncImagePainter("https://cdn2.thecatapi.com/images/${breedDetail.breed.referenceImageId}.jpg")
    val state by painter.state.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomTopBar(
                config = TopBarConfiguration(showBack = true, showFavorites = true),
                onBack = { onSystemAction(SystemAction.NavigateBack) },
                onAction = { onSystemAction(SystemAction.Navigate(Routes.Favorites)) }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAction(BreedAction.OnSaveAsFavorite(breedDetail)) },
                modifier = Modifier.padding(16.dp),
                containerColor = colorBlue,
                contentColor = Color.White
            ) {
                val tint = if(breedDetail.isFavorite) colorRed else Color.White

                Icon(
                    vectorResource(Res.drawable.heart_straight_fill_svgrepo_com),
                    "Favorite",
                    modifier = Modifier.size(50.dp),
                    tint = tint
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                when (state) {
                    is AsyncImagePainter.State.Error -> {
                        Image(
                            org.jetbrains.compose.resources.painterResource(Res.drawable.error_svgrepo_com),
                            contentDescription = "Error loading image",
                            modifier = Modifier
                                .weight(0.8f)
                                .aspectRatio(16f / 9f)
                        )
                    }
                    is AsyncImagePainter.State.Success -> {
                        Image(
                            painter = painter,
                            contentDescription = breedDetail.breed.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .clip(
                                    RoundedCornerShape(8.dp)
                                ),
                            contentScale = ContentScale.Crop
                        )
                    }
                    else -> {
                        CircularProgressIndicator( modifier = Modifier.size(150.dp))
                    }
                }


                Column(Modifier.padding(16.dp)) {
                    Text(
                        text = breedDetail.breed.name,
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = breedDetail.breed.description,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(Res.string.origin),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = breedDetail.breed.origin,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(Res.string.temperament),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = breedDetail.breed.temperament,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(Res.string.lifeSpan),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = breedDetail.breed.lifeSpan,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    )
}