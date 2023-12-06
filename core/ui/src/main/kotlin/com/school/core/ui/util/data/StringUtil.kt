package com.school.core.ui.util.data

fun String.idLengthCheck() = length in 8..15

fun String.pwLengthCheck() = length in 8..20

fun String.pwRegexCheck() =
    "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#\$%^&*?~])[a-zA-Z0-9!@#\$%^&*?~]{8,20}\$".toRegex()
        .matches(this)

fun String.nameLengthCheck() = length in 2..10

fun String.phoneNumberRegexCheck() =
    "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})\$".toRegex().matches(this)