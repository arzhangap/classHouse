package com.arzhang.project.classhouse.Screen.Article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arzhang.project.classhouse.Repository.CourseRepository
import com.arzhang.project.classhouse.database.model.Article
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ArticleViewModel @AssistedInject constructor(
    private val repository: CourseRepository,
    @Assisted val articleId: Int,
) : ViewModel() {
    private val _uiState = MutableStateFlow(Article())
    val uiState get() = _uiState

    init {
        getArticle()
    }

    private fun getArticle() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update {
                repository.getArticle(articleId)
            }
        }
    }

    @AssistedFactory
    interface ArticleViewModelFactory {
        fun create(id: Int) : ArticleViewModel
    }

}