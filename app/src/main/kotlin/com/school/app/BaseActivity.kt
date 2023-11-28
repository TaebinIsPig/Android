package com.school.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.school.core.design_system.SchoolTheme
import com.school.feature.intro.navigation.IntroNavigationItem
import com.school.feature.intro.navigation.introGraph
import com.school.feature.main.navigation.mainGraph
import com.school.feature.main.navigation.navigateMain
import com.school.feature.signin.navigation.navigateSignIn
import com.school.feature.signin.navigation.signInGraph
import com.school.feature.signup.navigation.navigateSignup
import com.school.feature.signup.navigation.signupGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberAnimatedNavController()
            SchoolTheme {
                BaseApp(navController = navController)
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BaseApp(navController: NavHostController) {
    AnimatedNavHost(
        navController = navController,
        startDestination = IntroNavigationItem.Intro.route,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { it * 2 }, animationSpec = tween(
                    durationMillis = 500
                )
            )
        },
        popEnterTransition = { fadeIn(animationSpec = tween(durationMillis = 500)) },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { it * 2 }, animationSpec = tween(
                    durationMillis = 500
                )
            )
        }
    ) {
        introGraph(
            navigateLogin = navController::navigateSignIn,
            navigateSignUp = navController::navigateSignup
        )
        signupGraph(
            popBackStack = navController::popBackStack,
            navigateMain = navController::navigateMain
        )
        signInGraph(
            popBackStack = navController::popBackStack,
            navigateMain = navController::navigateMain,
            navigateFindId = {},
            navigateFindPw = {})
        mainGraph(navigateProfile = {})
    }
}