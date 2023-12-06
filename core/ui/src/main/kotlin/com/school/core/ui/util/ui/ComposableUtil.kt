package com.school.core.ui.util.ui

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable

const val animationTime = 300
const val targetX = 1.5

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.mainComposable(
    isBackHome: Boolean,
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable (AnimatedVisibilityScope.(NavBackStackEntry) -> Unit),
) = composable(
    route = route,
    content = content,
    arguments = arguments,
    deepLinks = deepLinks,
    enterTransition = if (isBackHome) {
        {
            slideInHorizontally(
                initialOffsetX = { (it * targetX).toInt() }, animationSpec = tween(
                    durationMillis = animationTime,
                )
            )
        }
    } else {
        { fadeIn(initialAlpha = 1F, animationSpec = tween(durationMillis = animationTime)) }
    },
    exitTransition = {
        slideOutHorizontally(
            targetOffsetX = { (it * targetX).toInt() * -1 },
            animationSpec = tween(durationMillis = animationTime)
        )
    },
    popEnterTransition = {
        slideInHorizontally(
            initialOffsetX = { (it * targetX).toInt() * -1 }, animationSpec = tween(
                durationMillis = animationTime,
            )
        )
    },
    popExitTransition = {
        slideOutHorizontally(
            targetOffsetX = { (it * targetX).toInt() }, animationSpec = tween(
                durationMillis = animationTime
            )
        )
    }
)

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.extraComposable(
    isBackHome: Boolean,
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable (AnimatedVisibilityScope.(NavBackStackEntry) -> Unit),
) = composable(
    route = route,
    content = content,
    arguments = arguments,
    deepLinks = deepLinks,
    enterTransition = if (isBackHome) {
        {
            slideInHorizontally(
                initialOffsetX = { (it * targetX).toInt() }, animationSpec = tween(
                    durationMillis = animationTime,
                )
            )
        }
    } else {
        { EnterTransition.None }
    },
    exitTransition = if (isBackHome) {
        {
            slideOutHorizontally(
                targetOffsetX = { (it * targetX).toInt() * -1 },
                animationSpec = tween(durationMillis = animationTime)
            )
        }
    } else {
        { ExitTransition.None }
    },
    popExitTransition = if (isBackHome) {
        {
            slideOutHorizontally(
                targetOffsetX = { (it * targetX).toInt() }, animationSpec = tween(
                    durationMillis = animationTime
                )
            )
        }
    } else {
        { ExitTransition.None }
    }
)