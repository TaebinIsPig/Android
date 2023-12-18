package com.school.feature.account_management.navigation

enum class AccountManagementType(val title: String) {
    Signup(title = "SIGN UP"),
    FindID(title = "FIND ID"),
    FindPW(title = "FIND\nPASSWORD"),
    ChangeNumber(title = "CHANGE\nNUMBER"),
    ChangeSchool(title = "CHANGE\nSCHOOL")
}

sealed class AccountManagementNavigationItem(val route: String) {
    data object Signup : AccountManagementNavigationItem(route = "signup")

    data object FindID : AccountManagementNavigationItem(route = "findId")

    data object FindPw : AccountManagementNavigationItem(route = "findPw")

    data object ChangeNumber : AccountManagementNavigationItem(route = "changeNumber")

    data object ChangeSchool : AccountManagementNavigationItem(route = "changeSchool")
}

