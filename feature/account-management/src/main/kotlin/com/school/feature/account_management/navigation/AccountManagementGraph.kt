package com.school.feature.account_management.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.school.feature.account_management.account_management.AccountManagementScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.accountManagementGraph(
    popBackStack: () -> Unit,
    navigateSignIn: () -> Unit,
    savePhoneNumber: (String) -> Unit,
    saveSchool: (String) -> Unit,
) {
    composable(AccountManagementNavigationItem.Signup.route) {
        AccountManagementScreen(
            accountManagementType = AccountManagementType.Signup,
            popBackStack = popBackStack,
            navigateSignIn = navigateSignIn,
            savePhoneNumber = savePhoneNumber,
            saveSchool = saveSchool
        )
    }

    composable(AccountManagementNavigationItem.FindID.route) {
        AccountManagementScreen(
            accountManagementType = AccountManagementType.FindID,
            popBackStack = popBackStack,
            navigateSignIn = navigateSignIn,
            savePhoneNumber = savePhoneNumber,
            saveSchool = saveSchool
        )
    }

    composable(AccountManagementNavigationItem.FindPw.route) {
        AccountManagementScreen(
            accountManagementType = AccountManagementType.FindPW,
            popBackStack = popBackStack,
            navigateSignIn = navigateSignIn,
            savePhoneNumber = savePhoneNumber,
            saveSchool = saveSchool
        )
    }

    composable(AccountManagementNavigationItem.ChangeNumber.route) {
        AccountManagementScreen(
            accountManagementType = AccountManagementType.ChangeNumber,
            popBackStack = popBackStack,
            navigateSignIn = navigateSignIn,
            savePhoneNumber = savePhoneNumber,
            saveSchool = saveSchool
        )
    }

    composable(AccountManagementNavigationItem.ChangeSchool.route) {
        AccountManagementScreen(
            accountManagementType = AccountManagementType.ChangeSchool,
            popBackStack = popBackStack,
            navigateSignIn = navigateSignIn,
            savePhoneNumber = savePhoneNumber,
            saveSchool = saveSchool
        )
    }
}