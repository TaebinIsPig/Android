package com.school.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.school.app.bottom_navigation.SchoolBottomNavigation
import com.school.core.design_system.SchoolTheme
import com.school.feature.cafeteria.navigation.cafeteriaGraph
import com.school.feature.intro.navigation.IntroNavigationItem
import com.school.feature.intro.navigation.introGraph
import com.school.feature.main.navigation.mainGraph
import com.school.feature.main.navigation.navigateMain
import com.school.feature.signin.navigation.navigateSignIn
import com.school.feature.signin.navigation.signInGraph
import com.school.feature.account_management.navigation.navigateSignup
import com.school.feature.account_management.navigation.accountManagementGraph
import com.school.feature.account_management.navigation.navigateFindID
import com.school.feature.account_management.navigation.navigateFindPw
import com.school.feature.timetable.navigation.timetableGraph
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
                Scaffold(
                    bottomBar = { SchoolBottomNavigation(navController = navController) },
                    content = {
                        BaseApp(
                            modifier = Modifier.padding(it),
                            navController = navController
                        )
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BaseApp(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    AnimatedNavHost(
        modifier = modifier,
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
            navigateSignUp = navController::navigateSignup,
            navigateMain = { navController.navigateMain(IntroNavigationItem.Intro.route) }
        )
        accountManagementGraph(
            popBackStack = navController::popBackStack,
            navigateSignIn = { navController.navigateSignIn(IntroNavigationItem.Intro.route) }
        )
        signInGraph(
            popBackStack = navController::popBackStack,
            navigateMain = { navController.navigateMain(IntroNavigationItem.Intro.route) },
            navigateFindId = navController::navigateFindID,
            navigateFindPw = navController::navigateFindPw
        )
        mainGraph(navigateProfile = {})
        cafeteriaGraph()
        timetableGraph()
    }
}