package com.school.app.bottom_navigation

import com.school.core.design_system.attribute.SchoolIconList
import com.school.feature.cafeteria.navigation.CafeteriaNavigationItem
import com.school.feature.main.navigation.MainNavigationItem
import com.school.feature.schedule.navigation.ScheduleNavigationItem
import com.school.feature.timetable.navigation.TimetableNavigationItem

enum class BottomNavigationItem(
    val route: String,
    val title: String,
    val selectedIcon: SchoolIconList,
    val unselectedIcon: SchoolIconList,
) {
    Main(
        route = MainNavigationItem.Main.route,
        title = "메인",
        selectedIcon = SchoolIconList.NavMainSelected,
        unselectedIcon = SchoolIconList.NavMainUnselected
    ),
    Cafeteria(
        route = CafeteriaNavigationItem.Cafeteria.route,
        title = "급식",
        selectedIcon = SchoolIconList.NavCafeteriaSelected,
        unselectedIcon = SchoolIconList.NavCafeteriaUnselected
    ),
    Timetable(
        route = TimetableNavigationItem.Timetable.route,
        title = "시간표",
        selectedIcon = SchoolIconList.NavTimetableSelected,
        unselectedIcon = SchoolIconList.NavTimetableUnselected
    ),
    Schedule(
        route = ScheduleNavigationItem.Schedule.route,
        title = "학사일정",
        selectedIcon = SchoolIconList.NavScheduleSelected,
        unselectedIcon = SchoolIconList.NavTimetableUnselected
    ),
}