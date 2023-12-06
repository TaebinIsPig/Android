package com.school.feature.account_management.signup.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.school.feature.account_management.certificate.viewmodel.CertificateViewModel
import com.school.feature.account_management.signup.viewmodel.SignupViewModel
import com.school.feature.account_management.signup.search_school.SearchSchoolScreen
import com.school.feature.account_management.signup.write_sign_info.WriteSignInfoScreen

@OptIn(ExperimentalAnimationApi::class)
internal fun NavGraphBuilder.signupGraph(
    signupViewModel: SignupViewModel,
    certificateViewModel: CertificateViewModel,
    navigatePhoneNumber: () -> Unit,
    navigateSignIn: () -> Unit,
    popBackStack: () -> Unit,
) {
    composable(SignupNavigationItem.WriteSignInfo.route) {
        WriteSignInfoScreen(
            navigateSignIn = navigateSignIn,
            popBackStack = popBackStack,
            signupViewModel = signupViewModel,
            certificateViewModel = certificateViewModel
        )
    }

    composable(SignupNavigationItem.SearchSchool.route) {
        SearchSchoolScreen(
            navigatePhoneNumber = navigatePhoneNumber,
            signupViewModel = signupViewModel
        )
    }
}