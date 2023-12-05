package com.school.feature.account_management.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.school.feature.account_management.account_management.AccountManagementScreen

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