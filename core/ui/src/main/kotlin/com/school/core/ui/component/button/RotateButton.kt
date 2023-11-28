package com.school.core.ui.component.button

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.school.core.design_system.SchoolTheme
import com.school.core.design_system.attribute.SchoolIcon
import com.school.core.design_system.attribute.SchoolIconList
import com.school.core.ui.util.modifier.schoolClickable

@Composable
fun OpenDatePickerButton(
    modifier: Modifier = Modifier,
    isOpen: Boolean,
    onClick: () -> Unit,
) {
    var targetValue by remember { mutableFloatStateOf(180F) }
    val rotateValue: Float by animateFloatAsState(
        targetValue = targetValue,
        tween(500),
        label = "openDatePickerAnimation"
    )
    SchoolIcon(
        modifier = modifier
            .background(
                color = SchoolTheme.colors.main,
                shape = CircleShape
            )
            .padding(vertical = 1.dp, horizontal = 3.dp)
            .rotate(rotateValue)
            .schoolClickable(onClick = {
                onClick()
                targetValue = if (isOpen) 180F else 0F
            }),
        icon = SchoolIconList.OpenDatePicker
    )
}