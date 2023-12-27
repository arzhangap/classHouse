package com.arzhang.project.classhouse.Screen.Home.Main

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arzhang.project.classhouse.R
import com.arzhang.project.classhouse.database.model.Course
import com.arzhang.project.classhouse.ui.components.CourseCard
import com.arzhang.project.classhouse.ui.components.Slider
import com.arzhang.project.classhouse.ui.theme.ClassHouseTheme
import java.util.Locale


@Composable
fun MainScreen(
    onCourseClick: (Int) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val courses = uiState.latestCourses
    val scrollState = rememberScrollState()
    if(courses.isNotEmpty()) {
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            Slider()
            MainSection(title = R.string.latestPots, content = {
                CourseRow(courseList = courses, onCourseClick = { onCourseClick(it) })
            })
            MainSection(title = R.string.popular, content = {
                CourseRow(courseList = courses, onCourseClick = { onCourseClick(it) })
            })
            MainSection(title = R.string.editorschoice, content = {
                CourseRow(courseList = courses, onCourseClick = { onCourseClick(it) })
            })
        }
    } else {
        Column(verticalArrangement = Arrangement.Center) {
            CircularProgressIndicator()
            Text("لطفا صبور باشید")
        }
    }
    }

@Composable
fun MainSection(
    @StringRes title: Int,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            stringResource(id = title).uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 20.dp)
                .padding(horizontal = 16.dp)
        )
        content()

    }
}

@Composable
fun CourseRow(
    courseList: List<Course>,
    onCourseClick: (Int) -> Unit,
) {
    // Implement composable here
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) {
        items(courseList){
         CourseCard(course = it, onItemClick = {onCourseClick(it)})
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun MainScreenPreview() {
    ClassHouseTheme {
        MainScreen({})
    }
}