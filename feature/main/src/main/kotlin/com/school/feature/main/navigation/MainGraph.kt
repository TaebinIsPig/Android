package com.school.feature.main.navigation

import androidx.navigation.NavGraphBuilder
import com.school.core.ui.util.ui.mainComposable
import com.school.feature.main.main.MainScreen

fun NavGraphBuilder.mainGraph(
    isBackHome: Boolean,
    changePreviousRoute: (String) -> Unit,
    navigateProfile: () -> Unit,
) {
    mainComposable(
        route = MainNavigationItem.Main.route,
        isBackHome = isBackHome
    ) {
        MainScreen(navigateProfile = navigateProfile)
        changePreviousRoute(MainNavigationItem.Main.route)
    }
}