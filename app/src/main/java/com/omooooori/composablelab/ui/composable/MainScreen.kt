package com.omooooori.composablelab.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.omooooori.composablelab.ui.NavigationRoutes

@Composable
fun MainScreen(
    navController: NavController
) {
    val navigateToScreen: (String) -> Unit = { route ->
        navController.navigate(route)
    }

    val screenList = linkedMapOf(
        "Learning English UI" to NavigationRoutes.LEARNING_ENGLISH_UI,
        "TCA like Architecture" to NavigationRoutes.TCA_LIKE_ARCHITECTURE,
        "Image Loader Fragment" to NavigationRoutes.IMAGE_LOADER_FRAGMENT
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(screenList.entries.toList()) { (title, route) ->
            ListItem(
                title = title,
                onClick = { navigateToScreen(route) }
            )
        }
    }
}

@Composable
fun ListItem(
    title: String,
    onClick: () -> Unit
) {
    Box(modifier = Modifier
        .padding(horizontal = 24.dp, vertical = 8.dp)
        .fillMaxWidth()
        .background(color = Color.Cyan, shape = RoundedCornerShape(8.dp))
        .border(1.dp, Color.Black, shape = RoundedCornerShape(8.dp))
        .clickable { onClick() }
    ) {
        Text(
            text = title,
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 4.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(navController = rememberNavController())
}
