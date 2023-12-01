package com.school.feature.signin.navigation

import androidx.navigation.NavController

fun NavController.navigateSignIn(route: String? = null) =
    navigate(SignInNavigationItem.SignIn.route) {
        route?.let {
            popUpTo(it)
        }
    }