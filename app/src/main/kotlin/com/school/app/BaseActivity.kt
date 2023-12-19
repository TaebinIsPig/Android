package com.school.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.school.app.bottom_navigation.BottomNavigationItem
import com.school.app.bottom_navigation.SchoolBottomNavigation
import com.school.core.design_system.SchoolTheme
import com.school.feature.cafeteria.navigation.cafeteriaGraph
import com.school.feature.intro.navigation.IntroNavigationItem
import com.school.feature.intro.navigation.introGraph
import com.school.feature.main.navigation.mainGraph
import com.school.feature.main.navigation.navigateMain
import com.school.feature.schedule.navigation.scheduleGraph
import com.school.feature.signin.navigation.navigateSignIn
import com.school.feature.signin.navigation.signInGraph
import com.school.feature.account_management.navigation.navigateSignup
import com.school.feature.account_management.navigation.accountManagementGraph
import com.school.feature.account_management.navigation.navigateChangePhoneNumber
import com.school.feature.account_management.navigation.navigateChangeSchool
import com.school.feature.account_management.navigation.navigateFindID
import com.school.feature.account_management.navigation.navigateFindPw
import com.school.feature.main.navigation.MainNavigationItem
import com.school.feature.timetable.navigation.timetableGraph
import com.school.profile.navigation.navigateProfile
import com.school.profile.navigation.profileGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberAnimatedNavController()
            var previousRoute by remember { mutableStateOf("") }
            SchoolTheme {
                Scaffold(
                    bottomBar = {
                        SchoolBottomNavigation(navController = navController)
                    },
                    content = {
                        Box {
                            if (BottomNavigationItem.values()
                                    .any { it.route == navController.currentDestination?.route }
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(bottom = 60.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(0.3F)
                                            .background(SchoolTheme.colors.main)
                                    )
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(1F)
                                            .background(SchoolTheme.colors.white)
                                    )
                                }
                            }
                            BaseApp(
                                modifier = Modifier.padding(it),
                                navController = navController,
                                previousRoute = previousRoute,
                            ) {
                                previousRoute = it
                            }
                        }
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
    previousRoute: String,
    changePreviousRoute: (String) -> Unit,
) {
    AnimatedNavHost(
        modifier = modifier,
        navController = navController,
        startDestination = IntroNavigationItem.Intro.route,
    ) {
        val isBackHome = previousRoute == MainNavigationItem.Main.route
        introGraph(
            navigateSignIn = navController::navigateSignIn,
            navigateSignUp = navController::navigateSignup,
            navigateMain = navController::navigateMain
        )
        accountManagementGraph(
            popBackStack = navController::popBackStack,
            navigateSignIn = navController::navigateSignIn,
            savePhoneNumber = {
                navController.previousBackStackEntry?.savedStateHandle?.set(
                    "phoneNumber",
                    it
                )
            }
        )
        signInGraph(
            popBackStack = navController::popBackStack,
            navigateMain = navController::navigateMain,
            navigateFindId = navController::navigateFindID,
            navigateFindPw = navController::navigateFindPw
        )
        mainGraph(
            isBackHome = isBackHome,
            changePreviousRoute = changePreviousRoute,
            navigateProfile = navController::navigateProfile
        )
        profileGraph(
            popBackStack = navController::popBackStack,
            navigateChangePhoneNumber = navController::navigateChangePhoneNumber,
            navigateChangeSchool = navController::navigateChangeSchool,
            closeEditProfile = {
                navController.currentBackStackEntry?.savedStateHandle?.remove<String>("phoneNumber")
            },
            changePhoneNumber = {
                navController.currentBackStackEntry?.savedStateHandle?.get<String>(
                    "phoneNumber"
                ) ?: ""
            }

        )
        cafeteriaGraph(isBackHome = isBackHome, changePreviousRoute = changePreviousRoute)
        timetableGraph(isBackHome = isBackHome, changePreviousRoute = changePreviousRoute)
        scheduleGraph(isBackHome = isBackHome, changePreviousRoute = changePreviousRoute)
    }
}