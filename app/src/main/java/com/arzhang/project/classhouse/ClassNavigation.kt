package com.arzhang.project.classhouse

import androidx.annotation.StringRes

enum class HomeDestinations(@StringRes val address: Int) {
    MainScreen(R.string.main),
    Category(R.string.category),
    Profile(R.string.profile),
}