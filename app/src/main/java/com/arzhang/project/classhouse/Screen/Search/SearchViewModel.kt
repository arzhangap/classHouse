package com.arzhang.project.classhouse.Screen.Search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arzhang.project.classhouse.Repository.CourseRepository
import com.arzhang.project.classhouse.database.model.Course
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel @AssistedInject constructor(
    private val repository: CourseRepository,
    @Assisted val categoryId: Int,
) : ViewModel() {

    private val _uiState: MutableStateFlow<List<Course>> = MutableStateFlow(emptyList())
    val uiState get() = _uiState

    init {
        getCourses()
    }

     private fun getCourses() {
        viewModelScope.launch(Dispatchers.IO) {
           val courses = repository.getCategoryCourses(categoryId)
            _uiState.update {
                courses
            }
        }
    }

    @AssistedFactory
    interface SearchViewModelFactory {
        fun create(id: Int) : SearchViewModel
    }
}