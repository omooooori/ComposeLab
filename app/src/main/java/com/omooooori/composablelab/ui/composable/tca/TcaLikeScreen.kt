package com.omooooori.composablelab.ui.composable.tca

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TcaLikeScreen() {
    Scaffold(
        bottomBar = { TcaLikeScreenBottomNavigation() }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .background(color = Color.White)
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { QuickFilterModule() }
            item { MovieContent() }
            item { ShortMovieModule() }
            item { MovieContent() }
            item { MovieContent() }
            item { ShortMovieModule() }
        }
    }
}

@Preview
@Composable
fun TcaLikeScreenPreview() {
    TcaLikeScreen()
}
