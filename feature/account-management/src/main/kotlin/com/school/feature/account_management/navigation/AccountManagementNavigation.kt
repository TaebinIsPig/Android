package com.school.feature.account_management.navigation

import androidx.navigation.NavController

fun NavController.navigateSignup() = navigate(AccountManagementNavigationItem.Signup.route)

fun NavController.navigateFindID() = navigate(AccountManagementNavigationItem.FindID.route)

fun NavController.navigateFindPw() = navigate(AccountManagementNavigationItem.FindPw.route)

fun NavController.navigateChangePhoneNumber() =
    navigate(AccountManagementNavigationItem.ChangeNumber.route)

fun NavController.navigateChangeSchool() =
    navigate(AccountManagementNavigationItem.ChangeSchool.route)