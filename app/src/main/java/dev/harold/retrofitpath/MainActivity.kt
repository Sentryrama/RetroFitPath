package dev.harold.retrofitpath

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.harold.retrofitpath.navigation.AppNavigation
import dev.harold.retrofitpath.ui.theme.RetroFitPathTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetroFitPathTheme {
                AppNavigation(
                    navController = rememberNavController(),
                )
            }
        }
    }
}