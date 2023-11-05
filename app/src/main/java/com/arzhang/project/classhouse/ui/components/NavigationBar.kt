package com.arzhang.project.classhouse.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.arzhang.project.classhouse.HomeDestinations

data class  BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

@Composable
fun HomeNavigationBar(navController: NavHostController) {
    // uiState
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
     val barItems = listOf(
         BottomNavigationItem(
             title = HomeDestinations.MainScreen.name, selectedIcon = Icons.Filled.Home, unselectedIcon = Icons.Outlined.Home
         ),
         BottomNavigationItem(
             title = HomeDestinations.Category.name, selectedIcon = Icons.Filled.List, unselectedIcon = Icons.Outlined.List
         ),
         BottomNavigationItem(
             title = HomeDestinations.Profile.name, selectedIcon = Icons.Filled.Person, unselectedIcon = Icons.Outlined.Person
         )
     )
    NavigationBar {
        barItems.forEachIndexed { index, bottomNavigationItem ->
            NavigationBarItem(selected = selectedItem == index, onClick = {
                selectedItem = index
                navController.navigate(bottomNavigationItem.title)
                                                                          }, icon = {
                Icon(
                    imageVector = if(index == selectedItem) {
                        bottomNavigationItem.selectedIcon
                    } else {
                        bottomNavigationItem.unselectedIcon
                    },
                    contentDescription = bottomNavigationItem.title
                )
            })
        }
    }
}