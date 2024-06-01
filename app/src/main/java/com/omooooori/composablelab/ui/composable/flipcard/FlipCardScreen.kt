package com.omooooori.composablelab.ui.composable.flipcard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.omooooori.composablelab.ui.composable.expandview.ExpandView

@Composable
fun FlipCardScreen() {
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            val items = listOf(1, 2, 3)
            val configuration = LocalConfiguration.current
            val screenWidth = configuration.screenWidthDp.dp
            val itemSpacing = 8.dp
            val horizontalPadding = 12.dp
            val itemWidth = (screenWidth - horizontalPadding * 2 - itemSpacing * (items.size - 1) - 12.dp) / 2

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(itemSpacing),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = horizontalPadding)
            ) {
                items(items) {
                    when (it) {
                        1 -> Box(
                            modifier = Modifier
                                .width(itemWidth)
                                .height(155.dp)
                                .background(
                                    brush = Brush.linearGradient(
                                        colors = listOf(
                                            Color(0xFFF3F6FF),
                                            Color(0xFFA7BEFF)
                                        ),
                                        start = Offset(0f, 0f),
                                        end = Offset(1f, 1f)
                                    ),
                                    shape = RoundedCornerShape(12.dp)
                                )
                        )
                        2 -> ExpandView()
                        3 -> FlipCard3(
                            modifier = Modifier
                                .width(itemWidth)
                                .height(155.dp)
                        )
                    }
                }
            }
        }
    }
}



@Preview
@Composable
fun FlipCardScreenPreview() {
    FlipCardScreen()
}
