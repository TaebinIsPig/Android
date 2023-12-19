package com.school.profile.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.school.core.ui.util.ui.animationTime
import com.school.core.ui.util.ui.targetX
import com.school.profile.profile.ProfileScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.profileGraph(
    popBackStack: () -> Unit,
    navigateChangePhoneNumber: () -> Unit,
    navigateChangeSchool: () -> Unit,
    closeEditProfile: () -> Unit,
    changePhoneNumber: () -> String,
) {
    composable(
        route = ProfileNavigationItem.Profile.route,
        enterTransition = {
            fadeIn(
                animationSpec = tween(
                    durationMillis = animationTime,
                    delayMillis = animationTime
                )
            )
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { (it * targetX).toInt() * -1 },
                animationSpec = tween(durationMillis = animationTime)
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { (it * targetX).toInt() }, animationSpec = tween(
                    durationMillis = animationTime
                )
            )
        }
    ) {
        ProfileScreen(
            popBackStack = popBackStack,
            navigateChangePhoneNumber = navigateChangePhoneNumber,
            navigateChangeSchool = navigateChangeSchool,
            changePhoneNumber = changePhoneNumber,
            closeEditProfile = closeEditProfile
        )
    }
}