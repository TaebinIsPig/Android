package com.school.feature.account_management.certificate.navigation

internal sealed class CertificateNavigationItem(val route: String) {
    data object PhoneNumber : CertificateNavigationItem(route = "phoneNumber")

    data object Certificate : CertificateNavigationItem(route = "certificate")
}