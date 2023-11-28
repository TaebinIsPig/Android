package com.school.feature.timetable.navigation

sealed class TimetableNavigationItem(val route: String) {
    data object Timetable: TimetableNavigationItem(route = "timetable")
}
