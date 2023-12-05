package com.school.feature.account_management.account_management

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.school.core.design_system.attribute.SchoolIcon
import com.school.core.design_system.attribute.SchoolIconList
import com.school.core.ui.component.textview.FugazOneText
import com.school.core.ui.util.modifier.schoolClickable
import com.school.feature.account_management.certificate.navigation.CertificateNavigationItem
import com.school.feature.account_management.certificate.navigation.certificateGraph
import com.school.feature.account_management.certificate.navigation.navigateCertificate
import com.school.feature.account_management.certificate.navigation.navigatePhoneNumber
import com.school.feature.account_management.certificate.viewmodel.CertificateViewModel
import com.school.feature.account_management.find.navigation.findGraph
import com.school.feature.account_management.find.navigation.navigateFindID
import com.school.feature.account_management.find.navigation.navigateFindPw
import com.school.feature.account_management.find.navigation.navigateWriteID
import com.school.feature.account_management.find.viewmodel.FindViewModel
import com.school.feature.account_management.navigation.AccountManagementType
import com.school.feature.account_management.signup.navigation.SignupNavigationItem
import com.school.feature.account_management.signup.navigation.navigateWriteSignInfo
import com.school.feature.account_management.signup.navigation.signupGraph
import com.school.feature.account_management.signup.viewmodel.SignupViewModel

@OptIn(ExperimentalAnimationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun AccountManagementScreen(
    accountManagementType: AccountManagementType,
    popBackStack: () -> Unit,
    navigateSignIn: () -> Unit,
    signupViewModel: SignupViewModel = hiltViewModel(),
    findViewModel: FindViewModel = hiltViewModel(),
    certificateViewModel: CertificateViewModel = hiltViewModel(),
) {
    val navController = rememberAnimatedNavController()
    val container = signupViewModel.container
    val state = container.stateFlow.collectAsState().value
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        certificateViewModel.saveAccountManagementType(accountManagementType = accountManagementType)
    }

    Column(
        modifier = Modifier.schoolClickable {
            focusManager.clearFocus()
            keyboardController?.hide()
        }
    ) {
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
                        if (navController.currentBackStackEntry?.destination?.route == navController.graph.startDestinationRoute) {
                            if (state.schoolName.isNotEmpty()) {
                                signupViewModel.saveSchool(schoolName = "")
                            } else {
                                keyboardController?.hide()
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
                text = accountManagementType.title,
                textSize = 32,
                textAlign = TextAlign.Start
            )
        }
        Spacer(modifier = Modifier.height(34.dp))
        AnimatedNavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
            startDestination = when (accountManagementType) {
                AccountManagementType.Signup -> SignupNavigationItem.SearchSchool.route
                AccountManagementType.FindID -> CertificateNavigationItem.PhoneNumber.route
                AccountManagementType.FindPW -> CertificateNavigationItem.PhoneNumber.route
            }
        ) {
            signupGraph(
                signupViewModel = signupViewModel,
                certificateViewModel = certificateViewModel,
                navigatePhoneNumber = navController::navigatePhoneNumber,
                navigateSignIn = navigateSignIn
            )
            findGraph(
                findViewModel = findViewModel,
                certificateViewModel = certificateViewModel,
                navigateSignIn = navigateSignIn,
                navigateFindPw = navController::navigateFindPw
            )
            certificateGraph(
                certificateViewModel = certificateViewModel,
                navigateWriteSignInfo = navController::navigateWriteSignInfo,
                navigateCertificate = navController::navigateCertificate,
                navigateFindId = navController::navigateFindID,
                navigateWriteId = navController::navigateWriteID,
            )
        }
    }
}