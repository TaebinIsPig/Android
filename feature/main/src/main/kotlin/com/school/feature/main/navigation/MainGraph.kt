package com.school.feature.main.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.school.feature.main.main.MainScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.mainGraph(
    changePreviousRoute: (String) -> Unit,
    navigateProfile: () -> Unit,
) {
    composable(
        route = MainNavigationItem.Main.route,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { it * 2 }, animationSpec = tween(
                    durationMillis = 500,
                )
            )
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { it * 2 * -1 }, animationSpec = tween(durationMillis = 500)
            )
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { it * 2 * -1 }, animationSpec = tween(
                    durationMillis = 500,
                )
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { it * 2 }, animationSpec = tween(
                    durationMillis = 500
                )
            )
        }
    ) {
        MainScreen(navigateProfile = navigateProfile)
        changePreviousRoute(MainNavigationItem.Main.route)
    }
}