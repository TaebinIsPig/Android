package com.school.feature.account_management.find.navigation

internal sealed class FindNavigationItem(val route: String) {
    data object FindID : FindNavigationItem(route = "findID")

    data object FindPw : FindNavigationItem(route = "findPw")

    data object WriteID : FindNavigationItem(route = "writeID")
}