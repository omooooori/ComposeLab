package com.omooooori.composablelab

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.commit
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.omooooori.composablelab.ui.ImageLoaderFragment
import com.omooooori.composablelab.ui.NavigationRoutes
import com.omooooori.composablelab.ui.composable.LearningEnglishUI3
import com.omooooori.composablelab.ui.composable.MainScreen
import com.omooooori.composablelab.ui.composable.tca.TcaLikeScreen
import com.omooooori.composablelab.ui.theme.ComposableLabTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposableLabTheme {
                val context = LocalContext.current
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
                        composable(NavigationRoutes.TCA_LIKE_ARCHITECTURE) {
                            TcaLikeScreen()
                        }
                        composable(NavigationRoutes.IMAGE_LOADER_FRAGMENT) {
                            FragmentContainer()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FragmentContainer() {
    val context = LocalContext.current

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { ctx ->
            FrameLayout(ctx).apply {
                id = View.generateViewId()
            }
        },
        update = { frameLayout ->
            if (context is FragmentActivity) {
                context.supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace(frameLayout.id, ImageLoaderFragment.newInstance())
                }
            }
        }
    )
}
