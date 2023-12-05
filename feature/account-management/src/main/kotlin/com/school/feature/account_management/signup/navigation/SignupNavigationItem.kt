package com.school.feature.account_management.signup.navigation

internal sealed class SignupNavigationItem(val route: String) {
    data object WriteSignInfo : SignupNavigationItem(route = "writeSignInfo")

    data object SearchSchool : SignupNavigationItem(route = "searchSchool")
}