package com.school.core.ui.component.background

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.school.core.design_system.R
import com.school.core.ui.util.modifier.schoolClickable

@Composable
fun SchoolBackground(
    modifier: Modifier = Modifier,
    onClickAction: (() -> Unit)? = null,
    isSuccess: Boolean? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .schoolClickable(onClick = onClickAction)
    ) {
        Column {
            content()
        }
        if (isSuccess != null) {
            val composition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(if (isSuccess) R.raw.success_animation else R.raw.fail_animation)
            )
            val lottieAnimation = rememberLottieAnimatable()

            LaunchedEffect(composition) {
                lottieAnimation.animate(
                    composition = composition,
                    initialProgress = 0f
                )
            }

            LottieAnimation(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(200.dp),
                composition = composition,
            )
        }
    }
}