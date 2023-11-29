package com.arzhang.project.classhouse.Screen.Home.Main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arzhang.project.classhouse.Repository.CourseRepository
import com.arzhang.project.classhouse.database.model.Course
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MainUiState(
    var latestCourses: List<Course> = emptyList()
)


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: CourseRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: MutableStateFlow<MainUiState> = _uiState

    init {
        getLatestCourse()
    }

    private fun getLatestCourse() {
        viewModelScope.launch(Dispatchers.IO) {
            val courses = repository.getLatestCourses()
            _uiState.update {
                it.copy(latestCourses = courses)
            }
        }

    }
}