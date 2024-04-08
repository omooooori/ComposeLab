package com.omooooori.composablelab.ui.composable.tca

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun QuickFilterModule() {
    val labels = listOf(
        "すべて",
        "ゲーム",
        "音楽",
        "ミックス",
        "ライブ",
        "自転車",
        "料理",
        "ペット",
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
    ) {
        itemsIndexed(labels) { index, label ->
            QuickFilter(label = label, isSelected = index == 0)
        }
    }
}

@Composable
@Preview
fun QuickFilterModulePreview() {
    QuickFilterModule()
}
