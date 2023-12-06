package com.school.feature.timetable.navigation

import androidx.navigation.NavGraphBuilder
import com.school.core.ui.util.ui.extraComposable
import com.school.feature.timetable.timetable.TimetableScreen

fun NavGraphBuilder.timetableGraph(isBackHome: Boolean, changePreviousRoute: (String) -> Unit) {
    extraComposable(
        route = TimetableNavigationItem.Timetable.route,
        isBackHome = isBackHome
    ) {
        TimetableScreen()
        changePreviousRoute(TimetableNavigationItem.Timetable.route)
    }
}