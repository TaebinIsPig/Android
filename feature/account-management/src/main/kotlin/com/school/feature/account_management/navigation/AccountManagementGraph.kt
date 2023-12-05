package com.school.feature.account_management.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.school.feature.account_management.certificate.CertificateScreen
import com.school.feature.account_management.phone_number.PhoneNumberScreen
import com.school.feature.account_management.search_school.SearchSchoolScreen
import com.school.feature.account_management.account_management.AccountManagementScreen
import com.school.feature.account_management.account_management.SignupViewModel
import com.school.feature.account_management.write_sign_info.WriteSignInfoScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.accountManagementGraph(popBackStack: () -> Unit, navigateSignIn: () -> Unit) {
    composable(AccountManagementNavigationItem.Signup.route) {
        AccountManagementScreen(
            accountManagementType = AccountManagementType.Signup,
            popBackStack = popBackStack,
            navigateSignIn = navigateSignIn
        )
    }

    composable(AccountManagementNavigationItem.FindID.route) {
        AccountManagementScreen(
            accountManagementType = AccountManagementType.FindID,
            popBackStack = popBackStack,
            navigateSignIn = navigateSignIn
        )
    }

    composable(AccountManagementNavigationItem.FindPw.route) {
        AccountManagementScreen(
            accountManagementType = AccountManagementType.FindPW,
            popBackStack = popBackStack,
            navigateSignIn = navigateSignIn
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
internal fun NavGraphBuilder.signupGraph(
    signupViewModel: SignupViewModel,
    navigatePhoneNumber: () -> Unit,
    navigateCertificate: () -> Unit,
    navigateWriteSignInfo: () -> Unit,
    navigateMain: () -> Unit,
) {
    composable(SignupNavigationItem.WriteSignInfo.route) {
        WriteSignInfoScreen(
            navigateMain = navigateMain,
            signupViewModel = signupViewModel
        )
    }

    composable(SignupNavigationItem.PhoneNumber.route) {
        PhoneNumberScreen(
            navigateCertificate = navigateCertificate,
            signupViewModel = signupViewModel
        )
    }

    composable(SignupNavigationItem.Certificate.route) {
        CertificateScreen(
            navigateWriteSignInfo = navigateWriteSignInfo,
            signupViewModel = signupViewModel
        )
    }

    composable(SignupNavigationItem.SearchSchool.route) {
        SearchSchoolScreen(
            navigatePhoneNumber = navigatePhoneNumber,
            signupViewModel = signupViewModel
        )
    }
}