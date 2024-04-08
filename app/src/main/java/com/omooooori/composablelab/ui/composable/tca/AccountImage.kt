package com.omooooori.composablelab.ui.composable.tca

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AccountImage(
    size: Dp,
    backgroundColor: Color = Color.Red
) {
    Box(
        modifier = Modifier
            .size(size)
            .background(
                color = backgroundColor,
                shape = CircleShape
            )
    )
}

@Composable
@Preview
fun AccountImagePreview() {
    AccountImage(size = 24.dp)
}
