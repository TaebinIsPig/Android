package com.school.profile.navigation

sealed class ProfileNavigationItem(val route: String) {
    data object Profile : ProfileNavigationItem(route = "profile")
}