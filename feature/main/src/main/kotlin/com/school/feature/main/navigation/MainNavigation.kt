package com.school.feature.main.navigation

import androidx.navigation.NavController

fun NavController.navigateMain(route: String? = null) = navigate(MainNavigationItem.Main.route) {
    route?.let {
        popUpTo(route) {
            inclusive = true
        }
    }
}