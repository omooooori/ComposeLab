package com.omooooori.composablelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.omooooori.composablelab.ui.NavigationRoutes
import com.omooooori.composablelab.ui.composable.LearningEnglishUI3
import com.omooooori.composablelab.ui.composable.MainScreen
import com.omooooori.composablelab.ui.theme.ComposableLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposableLabTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = NavigationRoutes.MAIN_SCREEN
                    ) {
                        composable(NavigationRoutes.MAIN_SCREEN) {
                            MainScreen(navController)
                        }
                        composable(NavigationRoutes.LEARNING_ENGLISH_UI) {
                            LearningEnglishUI3()
                        }
                    }
                }
            }
        }
    }
}
