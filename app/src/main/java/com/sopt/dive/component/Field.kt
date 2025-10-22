package com.sopt.dive.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview(showBackground = true)
@Composable
fun Test() {
    var s by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth()) {
        Field(
            text = "ID",
            label = "ID 를 입력해라",
            value = s,
            onValueChange = { s = it },
            true,
            focusManager = LocalFocusManager.current,
            modifier = Modifier.fillMaxWidth().padding(5.dp)
        )

        Spacer(Modifier.height(20.dp))

        // 디버깅을 위한 Text
        Text(
            text = "현재 상태(s): $s",
            color = Color.Red,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun Field(text: String, label: String,
          value: String, onValueChange: (String) -> Unit,
          inputVisibility: Boolean, focusManager: FocusManager, modifier: Modifier = Modifier) {

    Column(modifier = modifier) {
        Text(text, fontSize = 20.sp)
        TextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            maxLines = 1,
            visualTransformation = if(! inputVisibility) PasswordVisualTransformation('*')
                else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}