package com.school.core.design_system.attribute

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.school.core.design_system.R
import javax.annotation.concurrent.Immutable

@Composable
fun SchoolIcon(
    modifier: Modifier = Modifier,
    icon: SchoolIconList,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
) {
    Image(
        modifier = modifier,
        painter = painterResource(id = icon.drawableId),
        contentDescription = icon.contentDescription,
        alignment = alignment,
        contentScale = contentScale,
        alpha = alpha,
        colorFilter = colorFilter
    )
}

@Immutable
class SchoolIconList private constructor(
    @DrawableRes val drawableId: Int,
    val contentDescription: String? = null,
) {
    companion object {
        @Stable
        val PasswordVisible = SchoolIconList(
            drawableId = R.drawable.ic_password_visible,
            contentDescription = "passwordVisibleIcon"
        )

        @Stable
        val PasswordHide = SchoolIconList(
            drawableId = R.drawable.ic_password_hide,
            contentDescription = "passwordHideIcon"
        )

        @Stable
        val SignupTopBackground = SchoolIconList(
            drawableId = R.drawable.ic_signup_top_background,
            contentDescription = "signupTopBackground"
        )

        @Stable
        val SignInTopBackground = SchoolIconList(
            drawableId = R.drawable.ic_signin_top_background,
            contentDescription = "signInTopBackground"
        )

        @Stable
        val Back = SchoolIconList(
            drawableId = R.drawable.ic_back,
            contentDescription = "back"
        )

        @Stable
        val Profile = SchoolIconList(
            drawableId = R.drawable.ic_profile,
            contentDescription = "profile"
        )

        @Stable
        val NavMainSelected = SchoolIconList(
            drawableId = R.drawable.ic_nav_main_selected,
            contentDescription = "navMainSelected"
        )

        @Stable
        val NavMainUnselected = SchoolIconList(
            drawableId = R.drawable.ic_nav_main_unselected,
            contentDescription = "navMainUnselected"
        )

        @Stable
        val NavCafeteriaSelected = SchoolIconList(
            drawableId = R.drawable.ic_nav_cafeteria_selected,
            contentDescription = "navCafeteriaSelected"
        )

        @Stable
        val NavCafeteriaUnselected = SchoolIconList(
            drawableId = R.drawable.ic_nav_cafeteria_unselected,
            contentDescription = "navCafeteriaUnselected"
        )

        @Stable
        val NavTimetableSelected = SchoolIconList(
            drawableId = R.drawable.ic_nav_timetable_selected,
            contentDescription = "navTimetableSelected"
        )

        @Stable
        val NavTimetableUnselected = SchoolIconList(
            drawableId = R.drawable.ic_nav_timetable_unselected,
            contentDescription = "navTimetableUnselected"
        )

        @Stable
        val NavScheduleSelected = SchoolIconList(
            drawableId = R.drawable.ic_nav_schedule_selected,
            contentDescription = "navScheduleSelected"
        )

        @Stable
        val NavScheduleUnselected = SchoolIconList(
            drawableId = R.drawable.ic_nav_schedule_unselected,
            contentDescription = "navScheduleUnSelected"
        )

        @Stable
        val Cafeteria = SchoolIconList(
            drawableId = R.drawable.ic_cafeteria,
            contentDescription = "cafeteria"
        )

        @Stable
        val Timetable = SchoolIconList(
            drawableId = R.drawable.ic_timetable,
            contentDescription = "timetable"
        )

        @Stable
        val Schedule = SchoolIconList(
            drawableId = R.drawable.ic_schedule,
            contentDescription = "schedule"
        )

        @Stable
        val CurrentDate = SchoolIconList(
            drawableId = R.drawable.ic_current_date,
            contentDescription = "currentDate"
        )

        @Stable
        val OpenDatePicker = SchoolIconList(
            drawableId = R.drawable.ic_open_datepicker,
            contentDescription = "openDatePicker"
        )

        @Stable
        val NextMonth = SchoolIconList(
            drawableId = R.drawable.ic_next_month,
            contentDescription = "nextMonth"
        )

        @Stable
        val PrevMonth = SchoolIconList(
            drawableId = R.drawable.ic_prev_month,
            contentDescription = "prevMonth"
        )

        @Stable
        val AddSchedule = SchoolIconList(
            drawableId = R.drawable.ic_add_schedule,
            contentDescription = "addSchedule"
        )
    }
}