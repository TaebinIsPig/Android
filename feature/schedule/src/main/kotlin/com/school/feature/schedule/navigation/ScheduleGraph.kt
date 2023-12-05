package com.school.feature.schedule.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.school.feature.schedule.schedule.ScheduleScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.scheduleGraph(isBackHome: Boolean, changePreviousRoute: (String) -> Unit) {
    composable(
        route = ScheduleNavigationItem.Schedule.route,
        enterTransition = if (isBackHome) {
            {
                slideInHorizontally(
                    initialOffsetX = { it * 2 }, animationSpec = tween(
                        durationMillis = 500,
                    )
                )
            }
        } else {
            { EnterTransition.None }
        },
        exitTransition = if (isBackHome) {
            {
                slideOutHorizontally(
                    targetOffsetX = { it * 2 * -1 }, animationSpec = tween(durationMillis = 500)
                )
            }
        } else {
            { ExitTransition.None }
        },
        popExitTransition = if (isBackHome) {
            {
                slideOutHorizontally(
                    targetOffsetX = { it * 2 }, animationSpec = tween(
                        durationMillis = 500
                    )
                )
            }
        } else {
            { ExitTransition.None }
        }
    ) {
        ScheduleScreen()
        changePreviousRoute(ScheduleNavigationItem.Schedule.route)
    }
}