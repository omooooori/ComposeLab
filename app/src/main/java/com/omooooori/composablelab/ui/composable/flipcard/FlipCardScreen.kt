package com.omooooori.composablelab.ui.composable.flipcard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FlipCardScreen() {
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            FlipCard3(
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.TopCenter)
            )
        }
    }
}



@Preview
@Composable
fun FlipCardScreenPreview() {
    FlipCardScreen()
}
