package com.school.feature.cafeteria.navigation

sealed class CafeteriaNavigationItem(val route: String) {
    data object Cafeteria : CafeteriaNavigationItem(route = "cafeteria")
}