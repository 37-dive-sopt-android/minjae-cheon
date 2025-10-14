package com.sopt.dive.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Header(text: String, modifier: Modifier = Modifier) {
    Text(text = text, modifier = modifier)
}