package com.school.profile.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.school.profile.profile.ProfileScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.profileGraph(popBackStack: () -> Unit, navigateSetting: () -> Unit) {
    composable(ProfileNavigationItem.Profile.route) {
        ProfileScreen(popBackStack = popBackStack, navigateSetting = navigateSetting)
    }
}