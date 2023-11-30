package com.school.feature.signup.navigation

import androidx.navigation.NavController

fun NavController.navigateSignup() = navigate(SignupNavigationItem.Signup.route) {
    popUpTo(InternalSignupNavigationItem.PhoneNumber.route)
}

fun NavController.navigatePhoneNumber() =
    navigate(InternalSignupNavigationItem.PhoneNumber.route)

fun NavController.navigateCertificate() =
    navigate(InternalSignupNavigationItem.Certificate.route)

fun NavController.navigateWriteSignInfo() =
    navigate(InternalSignupNavigationItem.WriteSignInfo.route)