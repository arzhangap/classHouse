package com.arzhang.project.classhouse.Screen.Home.Profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arzhang.project.classhouse.Repository.CourseRepository
import com.arzhang.project.classhouse.database.model.Course
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val repository: CourseRepository
) : ViewModel() {
    val uiState: StateFlow<List<Course>> = repository.getFavourites()
        .stateIn(
        scope = viewModelScope,
       started = SharingStarted.WhileSubscribed(),
       initialValue = emptyList()
    )
//    val uiState: MutableStateFlow<List<Course>> = _uiState

//   fun getFav() : MutableStateFlow<List<Course>> = repository.getFavourites().stateIn(
//       scope = viewModelScope,
//       started = SharingStarted.WhileSubscribed(),
//       initialValue = 0
//   )
}