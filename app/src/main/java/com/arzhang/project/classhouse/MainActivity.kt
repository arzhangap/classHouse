package com.arzhang.project.classhouse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.arzhang.project.classhouse.Local.LocalDataProvider
import com.arzhang.project.classhouse.Screen.Article.ArticleScreen
import com.arzhang.project.classhouse.Screen.Article.ArticleViewModel
import com.arzhang.project.classhouse.Screen.Course.CourseScreen
import com.arzhang.project.classhouse.Screen.Course.CourseViewModel.CourseViewModelFactory
import com.arzhang.project.classhouse.Screen.Home.Category.CategoryScreen
import com.arzhang.project.classhouse.Screen.Home.Main.MainScreen
import com.arzhang.project.classhouse.Screen.Home.Profile.ProfileScreen
import com.arzhang.project.classhouse.Screen.Search.SearchScreen
import com.arzhang.project.classhouse.Screen.Search.SearchViewModel.SearchViewModelFactory
import com.arzhang.project.classhouse.ui.components.HomeNavigationBar
import com.arzhang.project.classhouse.ui.components.MyAppBar
import com.arzhang.project.classhouse.ui.theme.ClassHouseTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var articleFactory: ArticleViewModel.ArticleViewModelFactory
    @Inject
    lateinit var serviceFactory: CourseViewModelFactory
    @Inject
    lateinit var searchFactory: SearchViewModelFactory

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                val navController = rememberNavController()
                ClassHouseTheme {
                    Scaffold(
                        topBar = {
                            if (false) {
                                MyAppBar(onBackButtonClick = { navController.navigateUp() })
                            }
                        },
                        bottomBar = { HomeNavigationBar(navController = navController) }
                    ) { it ->
                        NavHost(
                            navController = navController,
                            startDestination = HomeDestinations.MainScreen.name,
                            modifier = Modifier.padding(it)
                        ) {
                            composable(HomeDestinations.MainScreen.name) {
                                MainScreen(onCourseClick = { navController.navigate("course/$it") })
                            }
                            composable(HomeDestinations.Category.name) {
                                CategoryScreen(
                                    categories = LocalDataProvider.categories,
                                    onCategoryClick = { id, name ->
                                        navController.navigate("search/$id+$name")
                                    })
                            }
                            composable(HomeDestinations.Profile.name) {
                                ProfileScreen(onClick = { navController.navigate("course/$it") })

                            }
                            composable(
                                "course/{courseId}",
                                arguments = listOf(navArgument("courseId") {
                                    type = NavType.IntType
                                })
                            ) { backStackEntry ->
                                val id = backStackEntry.arguments?.getInt("courseId")
                                if(id != null) {
                                    val viewModel = serviceFactory.create(id)
                                    CourseScreen(
                                        viewModel = viewModel,
                                        onItemClick = { navController.navigate("article/$it") })
                                } else
                                {
                                    Text(text = "not found")}
                            }
                            composable(
                                "article/{articleId}",
                                arguments = listOf(navArgument("articleId") {
                                    type = NavType.IntType
                                })
                            ) { backStackEntry ->
                                val articleId = backStackEntry.arguments?.getInt("articleId")
                                if (articleId != null) {
                                    val viewModel = articleFactory.create(articleId)
                                    ArticleScreen(viewModel)
                                } else {
                                    Text("Article Not Found")
                                }
                            }
                            composable(
                                "search/{categoryId}+{categoryName}",
                                arguments = listOf(navArgument("categoryId") {
                                    type = NavType.IntType
                                },navArgument("categoryName") {
                                    type = NavType.StringType
                                })
                            ) { backStackEntry ->
                                val categoryId = backStackEntry.arguments?.getInt("categoryId")
                                val categoryName = backStackEntry.arguments?.getString("categoryName") ?: "دسته بندی"
                                if (categoryId != null) {
                                    val viewModel = searchFactory.create(categoryId)
                                    SearchScreen(viewModel, categoryName, onCourseClick = {navController.navigate("course/$it")})
                                } else {
                                    Text("Article Not Found")
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}

