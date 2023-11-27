package com.school.feature.signup.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.school.feature.signup.certificate.CertificateScreen
import com.school.feature.signup.phone_number.PhoneNumberScreen
import com.school.feature.signup.search_school.SearchSchoolScreen
import com.school.feature.signup.signup.SignupScreen
import com.school.feature.signup.signup.SignupViewModel
import com.school.feature.signup.write_sign_info.WriteSignInfoScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.signupGraph(popBackStack: () -> Unit, navigateMain: () -> Unit) {
    composable(SignupNavigationItem.Signup.route) {
        SignupScreen(popBackStack = popBackStack, navigateMain = navigateMain)
    }
}

@OptIn(ExperimentalAnimationApi::class)
internal fun NavGraphBuilder.internalSignupGraph(
    signupViewModel: SignupViewModel,
    navigatePhoneNumber: () -> Unit,
    navigateCertificate: () -> Unit,
    navigateSearchSchool: () -> Unit,
    navigateMain: () -> Unit,
) {
    composable(InternalSignupNavigationItem.WriteSignInfo.route) {
        WriteSignInfoScreen(
            navigatePhoneNumber = navigatePhoneNumber,
            signupViewModel = signupViewModel
        )
    }

    composable(InternalSignupNavigationItem.PhoneNumber.route) {
        PhoneNumberScreen(
            navigateCertificate = navigateCertificate,
            signupViewModel = signupViewModel
        )
    }

    composable(InternalSignupNavigationItem.Certificate.route) {
        CertificateScreen(navigateSearchSchool = navigateSearchSchool)
    }

    composable(InternalSignupNavigationItem.SearchSchool.route) {
        SearchSchoolScreen(navigateMain = navigateMain, signupViewModel = signupViewModel)
    }
}