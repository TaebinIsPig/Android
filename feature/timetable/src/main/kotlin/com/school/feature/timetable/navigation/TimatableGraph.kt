package com.school.feature.timetable.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.school.feature.timetable.timetable.TimetableScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.timetableGraph() {
    composable(TimetableNavigationItem.Timetable.route) {
        TimetableScreen()
    }
}