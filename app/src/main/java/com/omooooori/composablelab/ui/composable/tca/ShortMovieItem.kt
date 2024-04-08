package com.omooooori.composablelab.ui.composable.tca

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ShortMovieItem(
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(2/3f)
            .padding(start = 4.dp, end = 4.dp, bottom = 8.dp)
            .background(
                color = Color.Black,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Image(
            imageVector = Icons.Default.Face,
            colorFilter = ColorFilter.tint(Color.White),
            contentDescription = null,
            modifier = Modifier.matchParentSize()
        )

        Text(
            text = "【葬送のフリーレン】ユーベルが人気でた理由",
            fontSize = 14.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(4.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
@Preview
fun ShortMovieItemPreview() {
    ShortMovieItem(modifier = Modifier)
}
