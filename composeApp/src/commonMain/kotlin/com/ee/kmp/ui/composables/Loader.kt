package com.ee.kmp.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ee.kmp.ui.colorOrange
import com.ee.kmp.ui.colorRed
import com.ee.kmp.ui.colorWhite

@Composable
fun Loader(
    loading: Boolean,
    showBackground: Boolean = false
) {
    if(loading) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = if(showBackground) colorWhite else MaterialTheme.colorScheme.background.copy(alpha = 0.5F)
        ) {
            Column (
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(1F))
                CircularProgressIndicator(
                    modifier = Modifier.size(150.dp),
                    strokeWidth = 5.dp,
                    color = colorRed,
                    trackColor = colorOrange
                )
                Spacer(modifier = Modifier.weight(1F))
            }
        }
    }
}