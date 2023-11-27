package com.school.feature.intro.navigation

sealed class IntroNavigationItem(val route: String) {
    data object Intro : IntroNavigationItem(route = "intro")
}