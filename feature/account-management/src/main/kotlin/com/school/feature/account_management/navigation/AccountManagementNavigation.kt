package com.school.feature.account_management.navigation

import androidx.navigation.NavController

fun NavController.navigateSignup() = navigate(AccountManagementNavigationItem.Signup.route)

fun NavController.navigateFindID() = navigate(AccountManagementNavigationItem.FindID.route)

fun NavController.navigateFindPw() = navigate(AccountManagementNavigationItem.FindPw.route)