package com.ee.kmp.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import com.ee.kmp.data.model.Breed
import com.ee.kmp.ui.colorRed
import kmp.composeapp.generated.resources.Res
import kmp.composeapp.generated.resources.error_svgrepo_com
import kmp.composeapp.generated.resources.trash_alt_svgrepo_com
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun BreedCardPreview() {
    BreedCardPreview()
}

@Composable
fun BreedCard(
    breedCardType: BreedCardType = BreedCardType.Breed,
    breed: Breed,
    onClick: (Breed) -> Unit = {},
    onDelete: (String) -> Unit = {}
) {

    val painter = rememberAsyncImagePainter("https://cdn2.thecatapi.com/images/${breed.referenceImageId}.jpg")
    val state by painter.state.collectAsState()

    Card(
        modifier = Modifier.padding(top = 8.dp),
        onClick = { onClick(breed) }
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val imageModifier = Modifier
                .weight(0.8f)
                .aspectRatio(16f / 9f)

            when (state) {
                is AsyncImagePainter.State.Error -> {
                    Image(
                        painterResource(Res.drawable.error_svgrepo_com),
                        contentDescription = "Error loading image",
                        modifier = imageModifier
                    )
                }
                is AsyncImagePainter.State.Success -> {
                    Image(
                        painter = painter,
                        contentDescription = breed.name,
                        modifier = imageModifier,
                        contentScale = ContentScale.Fit
                    )
                }
                else -> {
                    CircularProgressIndicator( modifier = imageModifier)
                }
            }

            Spacer(modifier = Modifier.size(8.dp))

            Column(
                modifier = Modifier.weight(3f)
            ) {
                Text(breed.name)
                Text(
                    text = breed.description,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
            }

            if(breedCardType is BreedCardType.BreedAsFavorite) {
                IconButton(
                    onClick = { onDelete(breed.id) },
                    modifier = Modifier.size(50.dp).weight(1f)
                ) {
                    Icon(
                        vectorResource(Res.drawable.trash_alt_svgrepo_com),
                        "Delete from favorites",
                        tint = colorRed
                    )
                }
            }
        }
    }
}

sealed class BreedCardType() {
    object Breed : BreedCardType()
    object BreedAsFavorite : BreedCardType()
}