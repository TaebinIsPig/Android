package com.school.feature.account_management.navigation

enum class AccountManagementType(val title: String) {
    Signup(title = "SIGN UP"),
    FindID(title = "ID"),
    FindPW(title = "PASSWORD")
}

sealed class AccountManagementNavigationItem(val route: String) {
    data object Signup : AccountManagementNavigationItem(route = "signup")

    data object FindID : AccountManagementNavigationItem(route = "findId")

    data object FindPw : AccountManagementNavigationItem(route = "findPw")
}

internal sealed class SignupNavigationItem(val route: String) {
    data object WriteSignInfo : SignupNavigationItem(route = "writeSignInfo")

    data object PhoneNumber : SignupNavigationItem(route = "phoneNumber")

    data object Certificate : SignupNavigationItem(route = "certificate")

    data object SearchSchool : SignupNavigationItem(route = "searchSchool")
}

