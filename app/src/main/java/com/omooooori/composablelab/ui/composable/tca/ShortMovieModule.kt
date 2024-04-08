package com.omooooori.composablelab.ui.composable.tca

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ShortMovieModule() {
    Column {
        for (row in 0 until 2) {
            Row {
                for (column in 0 until 2) {
                    ShortMovieItem(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
@Preview
fun ShortMovieModulePreview() {
    ShortMovieModule()
}
