package com.school.core.ui.util.lifecycle

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun getActivity() = LocalContext.current as ComponentActivity