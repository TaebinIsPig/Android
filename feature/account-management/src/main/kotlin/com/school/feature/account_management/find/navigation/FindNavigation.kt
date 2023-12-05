package com.school.feature.account_management.find.navigation

import androidx.navigation.NavController
import com.school.feature.account_management.certificate.navigation.CertificateNavigationItem

internal fun NavController.navigateFindID() = navigate(FindNavigationItem.FindID.route) {
    popUpTo(CertificateNavigationItem.PhoneNumber.route)
}

internal fun NavController.navigateFindPw() = navigate(FindNavigationItem.FindPw.route) {
    popUpTo(CertificateNavigationItem.PhoneNumber.route)
}

internal fun NavController.navigateWriteID() = navigate(FindNavigationItem.WriteID.route) {
    popUpTo(CertificateNavigationItem.PhoneNumber.route)
}