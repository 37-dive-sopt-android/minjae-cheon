package com.sopt.dive.component

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.sopt.dive.ui.theme.LightGray

@Preview(showBackground = true)
@Composable
fun TestSignUpButton() {
    SignUpButton(
        onClick = { },
        modifier = Modifier
    )
}

@Composable
fun SignUpButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Text("회원가입하기", color = LightGray)
    }
}