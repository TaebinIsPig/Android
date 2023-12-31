package com.school.feature.intro.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.school.feature.intro.intro.IntroScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.introGraph(
    navigateSignIn: () -> Unit,
    navigateSignUp: () -> Unit,
    navigateMain: () -> Unit,
) {
    composable(route = IntroNavigationItem.Intro.route) {
        IntroScreen(
            navigateSignIn = navigateSignIn,
            navigateSignUp = navigateSignUp,
            navigateMain = navigateMain
        )
    }
}