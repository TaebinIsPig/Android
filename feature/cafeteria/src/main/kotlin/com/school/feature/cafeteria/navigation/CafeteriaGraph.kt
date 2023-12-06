package com.school.feature.cafeteria.navigation

import androidx.navigation.NavGraphBuilder
import com.school.core.ui.util.ui.extraComposable
import com.school.feature.cafeteria.cafeteria.CafeteriaScreen

fun NavGraphBuilder.cafeteriaGraph(isBackHome: Boolean, changePreviousRoute: (String) -> Unit) {
    extraComposable(
        route = CafeteriaNavigationItem.Cafeteria.route,
        isBackHome = isBackHome
    ) {
        CafeteriaScreen()
        changePreviousRoute(CafeteriaNavigationItem.Cafeteria.route)
    }
}