package com.school.feature.signin.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.school.feature.signin.signin.SignInScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.signInGraph(
    popBackStack: () -> Unit,
    navigateMain: () -> Unit,
    navigateFindId: () -> Unit,
    navigateFindPw: () -> Unit,
) {
    composable(SignInNavigationItem.SignIn.route) {
        SignInScreen(
            popBackStack = popBackStack,
            navigateMain = navigateMain,
            navigateFindId = navigateFindId,
            navigateFindPw = navigateFindPw
        )
    }
}