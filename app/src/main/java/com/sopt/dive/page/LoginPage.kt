package com.sopt.dive.page

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.component.CustomButton
import com.sopt.dive.component.Header
import com.sopt.dive.component.LoginField
import com.sopt.dive.component.SignUpButton


@Composable
fun LoginPage(modifier: Modifier = Modifier,
              onLoginClick: (id: String, pw: String) -> Unit,
              onSignUpClick: () -> Unit) {

    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(
            "Welcome To Sopt", fontSize = 30.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        )

        Spacer(Modifier.height(50.dp))

        LoginField(
            id, { id = it }, pw, { pw = it },
            focusManager, modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.weight(1f))

        CustomButton(
            "Welcome To Sopt", {
                if (id != "" && pw != "") {
                    onLoginClick(id, pw)
                } else {
                    Toast.makeText(context, "로그인 실패", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        SignUpButton({
            onSignUpClick()
        })

        Spacer(Modifier.height(5.dp))
    }
}
