package com.school.feature.signup.navigation

import androidx.navigation.NavController

fun NavController.navigateSignup() = navigate(SignupNavigationItem.Signup.route)

fun NavController.navigatePhoneNumber() =
    navigate(InternalSignupNavigationItem.PhoneNumber.route)

fun NavController.navigateCertificate() =
    navigate(InternalSignupNavigationItem.Certificate.route)

fun NavController.navigateSearchSchool() =
    navigate(InternalSignupNavigationItem.SearchSchool.route) {
        popUpTo(InternalSignupNavigationItem.PhoneNumber.route)
    }