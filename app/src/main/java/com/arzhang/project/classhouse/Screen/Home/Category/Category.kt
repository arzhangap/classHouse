package com.arzhang.project.classhouse.Screen.Home.Category

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.arzhang.project.classhouse.database.model.Category

@Composable
fun CategoryScreen(
    categories: List<Category>,
    onCategoryClick: (Int, String) -> Unit
) {
    LazyVerticalGrid(columns = GridCells.Fixed(3), contentPadding = PaddingValues(vertical = 10.dp),) {
        items(categories) {
            CategoryCard(category = it, Modifier.padding(vertical = 10.dp).clickable { onCategoryClick(it.id, it.name) })
        }
    }
}

@Composable
fun CategoryCard(
    category: Category,
    modifier: Modifier = Modifier
) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
            Image(painter = painterResource(category.image) ,contentDescription = null, contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape))
            Spacer(modifier = Modifier.padding(top = 5.dp))
            Text(text = category.name, style = MaterialTheme.typography.headlineSmall)
        }
    }
