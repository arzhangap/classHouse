package com.arzhang.project.classhouse.Screen.Course

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arzhang.project.classhouse.Repository.CourseRepository
import com.arzhang.project.classhouse.database.model.Article
import com.arzhang.project.classhouse.database.model.Course
import com.arzhang.project.classhouse.database.model.CourseUnit
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CourseUiState(
    val course: Course = Course(),
    val units: List<CourseUnit> = emptyList(),
    val articles: List<List<Article>> = emptyList()
)

class CourseViewModel @AssistedInject constructor(
    private val repository: CourseRepository,
    @Assisted val courseId: Int,
) : ViewModel() {
    private val _uiState = MutableStateFlow(CourseUiState())
    val uiState get() = _uiState

    init {
        makeReady()
    }

    private fun makeReady() {
        viewModelScope.launch(Dispatchers.IO) {
            getCourse()
            getUnits()
            getArticleForUnit()
        }
    }

    private fun getCourse() {
        _uiState.update {
            it.copy(
                repository.getCourse(courseId)
            )
        }
    }

    private fun getUnits() {
            val units = repository.getUnits(courseId)
            _uiState.update {
                it.copy(units = units)
            }
    }
    private fun getArticleForUnit() {
        val allArticles: MutableList<List<Article>> = mutableListOf()
            _uiState.value.units.forEach { courseUnit ->
                val unitArticle: MutableList<Article> = mutableListOf()
                repository.getArticlesForUnit(courseUnit.id).forEach {
                unitArticle.add(it)
            }
                allArticles.add(unitArticle)
            }
        _uiState.update { it.copy(articles = allArticles) }
    }

    fun updateFav() {
        val newCourse = _uiState.value.course.copy(isFav = !_uiState.value.course.isFav)
        _uiState.update { it.copy(course = newCourse) }
        viewModelScope.launch {
            repository.update(newCourse)
        }
    }

    @AssistedFactory
    interface CourseViewModelFactory {
        fun create(id: Int) : CourseViewModel
    }
}

