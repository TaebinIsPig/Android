package com.school.feature.cafeteria.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.school.feature.cafeteria.cafeteria.CafeteriaScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.cafeteriaGraph() {
    composable(CafeteriaNavigationItem.Cafeteria.route) {
        CafeteriaScreen()
    }
}