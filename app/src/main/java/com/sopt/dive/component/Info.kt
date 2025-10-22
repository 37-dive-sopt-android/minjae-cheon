package com.sopt.dive.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.sopt.dive.ui.theme.LightGray

@Preview(showBackground = true)
@Composable
fun TestInfo() {
    Info("testLabel", "test")
}

@Composable
fun Info(label: String, text: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(label, fontWeight = W600, fontSize = 50.sp)
        Text(text, fontSize = 25.sp, color = LightGray)
    }
}