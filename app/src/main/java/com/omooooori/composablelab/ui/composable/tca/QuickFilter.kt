package com.omooooori.composablelab.ui.composable.tca

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun QuickFilter(
    label: String,
    isSelected: Boolean
) {
    val (textColor, backgroundColor) = if (isSelected) {
        Pair(Color.White, Color.Black)
    } else {
        Pair(Color.Black, Color.LightGray)
    }

    Text(
        text = label,
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(vertical = 2.dp, horizontal = 8.dp),
        color = textColor
    )
}

@Composable
@Preview
fun QuickFilterNotSelectedPreview() {
    QuickFilter(
        label = "すべて",
        isSelected = false
    )
}
@Composable
@Preview
fun QuickFilterSelectedPreview() {
    QuickFilter(
        label = "すべて",
        isSelected = true
    )
}
