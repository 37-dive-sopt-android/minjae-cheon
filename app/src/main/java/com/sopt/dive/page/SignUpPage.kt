package com.sopt.dive.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.UserInfo
import com.sopt.dive.UserUIInfo
import com.sopt.dive.component.CustomButton
import com.sopt.dive.component.Header
import com.sopt.dive.component.SignUpField

@Composable
fun SignUpPage(userInfoList: List<UserUIInfo>,
               onSignUpClick: () -> Unit,
               modifier: Modifier = Modifier) {

    val focusManager = LocalFocusManager.current

    Column(modifier = modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Header("SIGN UP", fontSize = 20.sp, modifier = Modifier)

        Spacer(modifier = Modifier.padding(20.dp))

        SignUpField(userUIInfo = userInfoList, focusManager = focusManager)

        Spacer(Modifier.weight(1f))

        CustomButton(text = "회원가입하기",
            onClick = onSignUpClick,
            modifier = Modifier.fillMaxWidth())

        Spacer(Modifier.height(5.dp))
    }
}
