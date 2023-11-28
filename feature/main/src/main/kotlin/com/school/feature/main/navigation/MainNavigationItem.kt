package com.school.feature.main.navigation

sealed class MainNavigationItem(val route: String) {
    data object Main : MainNavigationItem(route = "main")
}
