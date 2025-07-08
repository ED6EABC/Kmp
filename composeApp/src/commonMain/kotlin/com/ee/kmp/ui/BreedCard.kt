package com.ee.kmp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.ee.kmp.data.Breed
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun BreedCardPreview() {
    BreedCardPreview()
}

@Composable
fun BreedCard(breed: Breed) {
    Card(modifier = Modifier.padding(top = 8.dp)) {
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
        }
    }
}