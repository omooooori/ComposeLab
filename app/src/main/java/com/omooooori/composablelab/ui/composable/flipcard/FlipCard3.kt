package com.omooooori.composablelab.ui.composable.flipcard

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Card
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
@Composable
fun FlipCard3(
    modifier: Modifier = Modifier,
    frontContent: @Composable () -> Unit = { FrontContent3() },
    backContent: @Composable () -> Unit = { BackContent3() }
) {
    var isFlipped by remember { mutableStateOf(false) }
    val rotation = remember { Animatable(0f) }
    val density = LocalDensity.current.density
    val coroutineScope = rememberCoroutineScope()

    Card(
        modifier = modifier
            .graphicsLayer(
                rotationY = rotation.value,
                cameraDistance = 8 * density
            )
            .clickable {
                coroutineScope.launch {
                    rotation.animateTo(
                        targetValue = if (isFlipped) 0f else 180f,
                        animationSpec = tween(durationMillis = 600, easing = FastOutSlowInEasing)
                    )
                    isFlipped = !isFlipped
                    rotation.animateTo(
                        targetValue = if (isFlipped) 180f else 0f,
                        animationSpec = tween(durationMillis = 0)
                    )
                }
            },
        backgroundColor = Color.Transparent,
        elevation = 8.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            if (rotation.value <= 90f || rotation.value >= 270f) {
                frontContent()
            } else {
                // If do this, the back view will be 180 degree rotated.
                //backContent()
                Box(
                    modifier = Modifier.graphicsLayer {
                        rotationY = 180f
                    }
                ) {
                    backContent()
                }
            }
        }
    }
}

@Composable
fun FrontContent3() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFF3F6FF),
                        Color(0xFFA7BEFF)
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(1f, 1f)
                )
            )
    ) {
        Text(
            text = "Front",
            fontSize = 24.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        RotatedIcon(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 12.dp, bottom = 12.dp)
        )
    }
}

@Composable
fun BackContent3() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            text = "Back",
            fontSize = 24.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )

        RotatedIcon(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 12.dp, bottom = 12.dp)
        )
    }
}

@Composable
fun RotatedIcon(
    modifier: Modifier
) {
    Icon(
        Icons.Default.Refresh,
        contentDescription = "",
        modifier = modifier
            .background(
                color = Color(0X0B000000),
                shape = CircleShape
            )
            .padding(4.dp)
    )
}


@Preview
@Composable
fun FlipCardP3review() {
    FlipCard3(
        modifier = Modifier
            .width(200.dp)
            .height(300.dp),
        frontContent = { FrontContent3() },
        backContent = { BackContent3() }
    )
}
