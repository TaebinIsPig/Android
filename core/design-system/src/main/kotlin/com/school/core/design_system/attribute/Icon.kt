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
    }
}