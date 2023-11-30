package com.school.feature.schedule.navigation

sealed class ScheduleNavigationItem(val route: String) {
    data object Schedule: ScheduleNavigationItem(route = "schedule")
}
