package com.school.feature.account_management.certificate.navigation

import androidx.navigation.NavController

internal fun NavController.navigatePhoneNumber() =
    navigate(CertificateNavigationItem.PhoneNumber.route)

internal fun NavController.navigateCertificate() =
    navigate(CertificateNavigationItem.Certificate.route)