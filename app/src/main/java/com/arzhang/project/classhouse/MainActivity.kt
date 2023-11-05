package com.arzhang.project.classhouse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.arzhang.project.classhouse.Screen.Home.Category.CategoryScreen
import com.arzhang.project.classhouse.Screen.Home.Main.MainScreen
import com.arzhang.project.classhouse.Screen.Home.Profile.ProfileScreen
import com.arzhang.project.classhouse.ui.components.HomeNavigationBar
import com.arzhang.project.classhouse.ui.theme.ClassHouseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ClassHouseTheme {
                Scaffold(
                    bottomBar = {HomeNavigationBar(navController = navController)}
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = HomeDestinations.MainScreen.name,
                        modifier = Modifier.padding(it)
                    ) {
                        composable(HomeDestinations.MainScreen.name) {
                            MainScreen(navController)
                        }
                        composable(HomeDestinations.Category.name) {
                            CategoryScreen()
                        }
                        composable(HomeDestinations.Profile.name) {
                            ProfileScreen()
                        }
                        composable("article/{articleId}",
                            arguments = listOf(navArgument("articleId") {type = NavType.IntType})
                            ) { backStackEntry ->
                            val string = backStackEntry.arguments?.getInt("articleId") ?: "NOT FOUND"
                            Text("$string")
                        }
                    }

                }
            }
        }
    }
}

