package com.sopt.dive.component

import android.util.Size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

@Composable
fun Header(text: String, fontSize: TextUnit, modifier: Modifier = Modifier) {
    Text(text = text, fontSize = fontSize, textAlign = TextAlign.Center, modifier = modifier)
}