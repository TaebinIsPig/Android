package com.school.feature.signup.navigation

sealed class SignupNavigationItem(val route: String) {
    data object Signup : SignupNavigationItem(route = "signup")
}

internal sealed class InternalSignupNavigationItem(val route: String) {
    data object WriteSignInfo : InternalSignupNavigationItem(route = "writeSignInfo")

    data object PhoneNumber : InternalSignupNavigationItem(route = "phoneNumber")

    data object Certificate : InternalSignupNavigationItem(route = "certificate")

    data object SearchSchool : InternalSignupNavigationItem(route = "searchSchool")
}