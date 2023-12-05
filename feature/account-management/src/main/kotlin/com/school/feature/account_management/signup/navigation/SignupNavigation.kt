package com.school.feature.account_management.signup.navigation

import androidx.navigation.NavController
import com.school.feature.account_management.certificate.navigation.CertificateNavigationItem

internal fun NavController.navigateWriteSignInfo() =
    navigate(SignupNavigationItem.WriteSignInfo.route) {
        popUpTo(CertificateNavigationItem.PhoneNumber.route)
    }