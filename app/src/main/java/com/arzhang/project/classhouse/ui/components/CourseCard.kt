package com.arzhang.project.classhouse.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arzhang.project.classhouse.Database.model.Course
import com.arzhang.project.classhouse.ui.theme.ClassHouseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseCard(
    course: Course,
    onItemClick: (Int) -> Unit ,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier.size(width = 250.dp, height = 150.dp),
        shape = RoundedCornerShape(20),
        onClick = { onItemClick(course.id) },
    ) {
            CourseCardImage(
                course = course,
                Modifier.weight(1f)
            )
                Text(
                    text = course.name,
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Right,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 5.dp)
                )
    }
}

@Composable
private fun CourseCardImage(course: Course, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(context.resources.getIdentifier(course.image,"drawable",context.packageName)),
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview
@Composable
fun CourseCardPreview() {
    ClassHouseTheme {
        CourseCard(course = Course(1, "dd", "python", 1, false), onItemClick = {})
    }

}