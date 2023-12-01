package com.school.core.design_system.attribute

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class SchoolColor(
    val main: Color,
    val pink: Color,
    val pink2: Color,
    val pink3: Color,
    val gray: Color,
    val gray2: Color,
    val lightGray: Color,
    val lightGray2: Color,
    val white: Color,
    val black: Color,
    val error: Color,
)

internal val LocalSchoolColors = staticCompositionLocalOf {
    SchoolColor(
        main = Color.Unspecified,
        pink = Color.Unspecified,
        pink2 = Color.Unspecified,
        pink3 = Color.Unspecified,
        gray = Color.Unspecified,
        gray2 = Color.Unspecified,
        lightGray = Color.Unspecified,
        lightGray2 = Color.Unspecified,
        white = Color.Unspecified,
        black = Color.Unspecified,
        error = Color.Unspecified
    )
}

val lightColor = SchoolColor(
    main = Color(0xFFFF9688),
    pink = Color(0xFFFFB5AB),
    pink2 = Color(0xFFFFCFCF),
    pink3 = Color(0xFFFF8989),
    gray = Color(0xFF969696),
    gray2 = Color(0xFFA9A9A9),
    lightGray = Color(0xFFCACACA),
    lightGray2 = Color(0xFFE6E6E6),
    white = Color(0xFFFFFFFF),
    black = Color(0xFF000000),
    error = Color(0xFFFF0000),
)

val darkColor = SchoolColor(
    main = Color(0xFFFF9688),
    pink = Color(0xFFFFB5AB),
    pink2 = Color(0xFFFFCFCF),
    pink3 = Color(0xFFFF8989),
    gray = Color(0xFF969696),
    gray2 = Color(0xFFA9A9A9),
    lightGray = Color(0xFFCACACA),
    lightGray2 = Color(0xFFE6E6E6),
    white = Color(0xFFFFFFFF),
    black = Color(0xFF000000),
    error = Color(0xFFFF0000),
)