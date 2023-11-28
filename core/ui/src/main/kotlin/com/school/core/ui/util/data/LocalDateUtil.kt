package com.school.core.ui.util.data

import java.time.LocalDate
import java.time.format.TextStyle
import java.time.temporal.TemporalAdjusters
import java.util.Locale

val dayOfWeekList = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")

fun LocalDate.getSpaceCount() = dayOfWeekList.indexOf(
    getStartOrLastDate(isStart = true).getDayOfWeek(Locale.US)
)

fun LocalDate.setDate(date: Int) = LocalDate.of(year, month.value, date)

fun LocalDate.getStartOrLastDate(isStart: Boolean) =
    with(if (isStart) TemporalAdjusters.firstDayOfMonth() else TemporalAdjusters.lastDayOfMonth())

fun LocalDate.toDisplayDate() = "${month.value}월 ${dayOfMonth}일 (${getDayOfWeek(Locale.KOREA)})"

fun LocalDate.getDayOfWeek(locale: Locale) = dayOfWeek.getDisplayName(TextStyle.SHORT, locale)