package com.school.feature.signin.navigation

sealed class SignInNavigationItem(val route: String) {
    data object SignIn : SignInNavigationItem(route = "signIn")
}