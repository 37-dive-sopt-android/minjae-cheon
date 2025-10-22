package com.sopt.dive.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun TestLogin() {
    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        LoginField(id, { id = it }, pw, { pw = it },
            LocalFocusManager.current, modifier = Modifier.fillMaxWidth())

        Text(id)
        Text(pw)
    }
}

@Composable
fun LoginField(
    id: String, onChangeId: (String) -> Unit,
    pw: String, onChangePw: (String) -> Unit,
    focusManager: FocusManager,
    modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
        Field("ID", "아이디를 입력해주세요", id, onChangeId,
            true, focusManager)
        Field("PW", "비밀번호를 입력해주세요", pw, onChangePw,
            false, focusManager)
    }
}