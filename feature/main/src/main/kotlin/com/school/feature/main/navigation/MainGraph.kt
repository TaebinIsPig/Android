package com.school.feature.main.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.school.feature.main.main.MainScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.mainGraph(navigateProfile: () -> Unit) {
    composable(MainNavigationItem.Main.route) {
        MainScreen(navigateProfile = navigateProfile)
    }
}