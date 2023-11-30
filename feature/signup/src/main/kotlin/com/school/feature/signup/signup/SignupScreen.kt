package com.school.feature.signup.signup

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.school.core.design_system.attribute.SchoolIcon
import com.school.core.design_system.attribute.SchoolIconList
import com.school.core.ui.component.textview.FugazOneText
import com.school.core.ui.util.modifier.schoolClickable
import com.school.feature.signup.navigation.InternalSignupNavigationItem
import com.school.feature.signup.navigation.internalSignupGraph
import com.school.feature.signup.navigation.navigateCertificate
import com.school.feature.signup.navigation.navigatePhoneNumber
import com.school.feature.signup.navigation.navigateWriteSignInfo

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SignupScreen(
    popBackStack: () -> Unit,
    navigateMain: () -> Unit,
    signupViewModel: SignupViewModel = hiltViewModel(),
) {
    val navController = rememberAnimatedNavController()
    val container = signupViewModel.container
    val state = container.stateFlow.collectAsState().value

    Column {
        Box {
            SchoolIcon(
                modifier = Modifier
                    .fillMaxWidth(),
                icon = SchoolIconList.SignupTopBackground,
                contentScale = ContentScale.FillWidth
            )
            SchoolIcon(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 20.dp, start = 16.dp)
                    .schoolClickable {
                        if (navController.currentBackStackEntry?.destination?.route == InternalSignupNavigationItem.SearchSchool.route) {
                            if (state.schoolName.isNotEmpty()) {
                                signupViewModel.saveSchool(schoolName = "")
                            } else {
                                popBackStack()
                            }
                        } else {
                            navController.popBackStack()
                        }
                    },
                icon = SchoolIconList.Back
            )
            FugazOneText(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 88.dp, start = 50.dp),
                text = "SIGN UP",
                textSize = 32
            )
        }
        Spacer(modifier = Modifier.height(34.dp))
        AnimatedNavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
            startDestination = InternalSignupNavigationItem.SearchSchool.route
        ) {
            internalSignupGraph(
                signupViewModel = signupViewModel,
                navigatePhoneNumber = navController::navigatePhoneNumber,
                navigateCertificate = navController::navigateCertificate,
                navigateWriteSignInfo = navController::navigateWriteSignInfo,
                navigateMain = navigateMain
            )
        }
    }
}