package com.ee.kmp.ui.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kmp.composeapp.generated.resources.Res
import kmp.composeapp.generated.resources.back_svgrepo_com
import kmp.composeapp.generated.resources.cats
import kmp.composeapp.generated.resources.heart_straight_fill_svgrepo_com
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun CustomTopBarPreview() {
    CustomTopBar(
        TopBarConfiguration.Default,
        {},
        {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    config: TopBarConfiguration,
    onBack: () -> Unit = {},
    onAction: () -> Unit
) {
    TopAppBar(
        title = {
            val label = if(config is TopBarConfiguration.BackAndFavorites) {
                config.title ?: stringResource(Res.string.cats)
            } else {
                stringResource(Res.string.cats)
            }
            Text(text = label)
        },
        navigationIcon = {
            if(config is TopBarConfiguration.BackAndFavorites)
                IconButton(
                    onClick = onBack,
                    modifier = Modifier.size(50.dp)
                ) {
                    Icon(
                        imageVector = vectorResource(Res.drawable.back_svgrepo_com),
                        contentDescription = "Back"
                    )
                }
        },
        actions = {
            if(config is TopBarConfiguration.BackAndFavorites)
                IconButton(
                    onClick = onAction,
                    modifier = Modifier.size(50.dp)
                ) {
                    Icon(
                        imageVector = vectorResource(Res.drawable.heart_straight_fill_svgrepo_com),
                        contentDescription = "Favorites"
                    )
                }
        }
    )
}

sealed class TopBarConfiguration() {
    object Default : TopBarConfiguration()
    data class BackAndFavorites(val title: String?) : TopBarConfiguration()
}