package com.school.feature.account_management.certificate.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.school.feature.account_management.certificate.send_certificate.PhoneNumberScreen
import com.school.feature.account_management.certificate.verify_code.CertificateScreen
import com.school.feature.account_management.certificate.viewmodel.CertificateViewModel

@OptIn(ExperimentalAnimationApi::class)
internal fun NavGraphBuilder.certificateGraph(
    certificateViewModel: CertificateViewModel,
    popBackStack: () -> Unit,
    navigateCertificate: () -> Unit,
    navigateWriteSignInfo: () -> Unit,
    navigateFindId: () -> Unit,
    navigateWriteId: () -> Unit,
) {
    composable(CertificateNavigationItem.PhoneNumber.route) {
        PhoneNumberScreen(
            navigateCertificate = navigateCertificate,
            certificateViewModel = certificateViewModel
        )
    }

    composable(CertificateNavigationItem.Certificate.route) {
        CertificateScreen(
            popBackStack = popBackStack,
            navigateWriteSignInfo = navigateWriteSignInfo,
            certificateViewModel = certificateViewModel,
            navigateFindId = navigateFindId,
            navigateWriteId = navigateWriteId
        )
    }
}