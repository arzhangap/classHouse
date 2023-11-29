package com.arzhang.project.classhouse.Screen.Course

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.arzhang.project.classhouse.Repository.CourseRepository
import com.arzhang.project.classhouse.database.model.Article
import com.arzhang.project.classhouse.database.model.CourseUnit
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CourseUiState(
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
            getUnits()
            getArticleForUnit()
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

    @AssistedFactory
    interface CourseViewModelFactory {
        fun create(id: Int) : CourseViewModel
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun provideFactory(
            assistedFactory: CourseViewModelFactory, // this is the Factory interface
            // declared above
            courseId: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(courseId) as T
            }
        }
    }
}

