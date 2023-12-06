package com.school.feature.account_management.find.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.school.feature.account_management.certificate.viewmodel.CertificateViewModel
import com.school.feature.account_management.find.find_id.FindIDScreen
import com.school.feature.account_management.find.find_pw.FindPwScreen
import com.school.feature.account_management.find.viewmodel.FindViewModel
import com.school.feature.account_management.find.write_id.WriteIDScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.findGraph(
    findViewModel: FindViewModel,
    certificateViewModel: CertificateViewModel,
    navigateSignIn: () -> Unit,
    navigateFindPw: () -> Unit,
    popBackStack: () -> Unit,
) {
    composable(FindNavigationItem.FindID.route) {
        FindIDScreen(
            navigateSignIn = navigateSignIn,
            popBackStack = popBackStack,
            findViewModel = findViewModel,
            certificateViewModel = certificateViewModel
        )
    }

    composable(FindNavigationItem.FindPw.route) {
        FindPwScreen(
            navigateSignIn = navigateSignIn,
            popBackStack = popBackStack,
            findViewModel = findViewModel,
            certificateViewModel = certificateViewModel
        )
    }

    composable(FindNavigationItem.WriteID.route) {
        WriteIDScreen(navigateFindPw = navigateFindPw, findViewModel = findViewModel)
    }
}