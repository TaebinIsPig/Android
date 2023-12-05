package com.school.feature.signin.navigation

import androidx.navigation.NavController

fun NavController.navigateSignIn() =
    navigate(SignInNavigationItem.SignIn.route) {
        popUpTo(graph.startDestinationRoute ?: return@navigate)
    }