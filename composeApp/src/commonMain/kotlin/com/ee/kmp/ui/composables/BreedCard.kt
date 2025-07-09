package com.ee.kmp.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.ee.kmp.data.Breed
import kmp.composeapp.generated.resources.Res
import kmp.composeapp.generated.resources.heart_straight_fill_svgrepo_com
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
    onClick: (Breed) -> Unit = {}
) {
    Card(
        modifier = Modifier.padding(top = 8.dp),
        onClick = { onClick(breed) }
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = "https://cdn2.thecatapi.com/images/${breed.referenceImageId}.jpg",
                contentDescription = breed.name,
                modifier = Modifier.size(50.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.size(8.dp))

            Column {
                breed.name?.let {
                    Text(it)
                }
                breed.description?.let {
                    Text(
                        text = it,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2
                    )
                }
            }

            if(breedCardType is BreedCardType.BreedAsFavorite) {
                IconButton(
                    onClick = { /*TODO*/ },
                ) {
                    Icon(vectorResource(Res.drawable.heart_straight_fill_svgrepo_com), "")
                }
            }
        }
    }
}

sealed class BreedCardType() {
    object Breed : BreedCardType()
    object BreedAsFavorite : BreedCardType()
}