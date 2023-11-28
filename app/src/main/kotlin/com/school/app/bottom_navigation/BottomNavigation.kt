package com.school.app.bottom_navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.school.core.design_system.SchoolTheme
import com.school.core.design_system.attribute.SchoolIcon
import com.school.core.ui.component.textview.BodyMediumText
import com.school.feature.main.navigation.MainNavigationItem

@Composable
fun SchoolBottomNavigation(
    navController: NavController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val itemList = BottomNavigationItem.values().toList()
    AnimatedVisibility(
        visible = itemList.map { it.route }.contains(currentRoute),
        enter = slideInVertically(
            initialOffsetY = { 0 },
            animationSpec = tween(durationMillis = 500)
        ),
        exit = slideOutVertically(
            targetOffsetY = { 0 },
            animationSpec = tween(durationMillis = 500)
        )
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(SchoolTheme.colors.lightGray2)
            )
            BottomNavigation(
                modifier = Modifier.height(60.dp),
                backgroundColor = SchoolTheme.colors.white,
                contentColor = SchoolTheme.colors.black
            ) {
                itemList.forEach { item ->
                    val isSelected = currentRoute == item.route
                    BottomNavigationItem(
                        icon = {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(4.dp),
                                horizontalAlignment = CenterHorizontally
                            ) {
                                SchoolIcon(icon = if (isSelected) item.selectedIcon else item.unselectedIcon)
                                BodyMediumText(
                                    text = item.title,
                                    color = if (isSelected) SchoolTheme.colors.main else SchoolTheme.colors.black,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        },
                        selected = isSelected,
                        onClick = {
                            if (navController.currentDestination?.route != item.route) {
                                navController.navigate(item.route) {
                                    popUpTo(MainNavigationItem.Main.route) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}