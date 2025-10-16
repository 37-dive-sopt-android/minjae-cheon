package com.sopt.dive.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.sopt.dive.R
import com.sopt.dive.UserInfo
import com.sopt.dive.component.CustomButton
import com.sopt.dive.component.Header
import com.sopt.dive.component.SignUpField
import com.sopt.dive.ui.theme.DiveTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setContent {
            DiveTheme {
                val scope = rememberCoroutineScope()
                val snackbarHostState = remember { SnackbarHostState() }

                Scaffold(modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { innerPadding ->
                    SignUpPage(modifier = Modifier.padding(innerPadding),
                        snackbarHostState = snackbarHostState,
                        scope = scope,
                        signUpFunc = { userInfo ->
                            val resultIntent = Intent()
                            userInfo.setIntent(resultIntent)
                            setResult(RESULT_OK, resultIntent)
                            finish()
                })
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpPage(modifier: Modifier = Modifier,
               snackbarHostState: SnackbarHostState = SnackbarHostState(),
               scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
               signUpFunc: (UserInfo) -> Unit = {}) {
    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var mbti by remember { mutableStateOf("") }

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    Column(modifier = modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Header("SIGN UP", fontSize = 20.sp, modifier = Modifier)

        Spacer(modifier = Modifier.padding(20.dp))

        SignUpField(
            id, { id = it }, pw, { pw = it },
            nickname, { nickname = it }, mbti, { mbti = it },
            focusManager)

        Spacer(Modifier.weight(1f))

        CustomButton("회원가입하기", {
            val result = validate(id, pw, nickname, mbti)
            if (result != ValidateResult.SUCCESS) {
                // error snack bar
                scope.launch {
                    snackbarHostState.showSnackbar("회원가입 실패: ${result.msg}")
                }
                return@CustomButton
            }
            // 성공 메세지
            Toast.makeText(context, "회원가입 성공!", Toast.LENGTH_SHORT).show()
            // LoginActivity 로 넘어가기(with info)
            val userInfo = UserInfo(id, pw, nickname, mbti)
            signUpFunc(userInfo)

        }, Modifier.fillMaxWidth())

        Spacer(Modifier.height(5.dp))
    }
}

fun validate(id: String, pw: String, nickname: String, mbti: String): ValidateResult {
    if(!(6 <= id.length && id.length <= 10)) {
        return ValidateResult.ID_FAULT
    }
    if(!(8 <= pw.length && pw.length <= 12)) {
        return ValidateResult.PW_FAULT
    }
    if(nickname.trim().isEmpty()) {
        return ValidateResult.NICKNAME_FAULT
    }

    if(! "[a-zA-Z]{4}".toRegex().matches(mbti)) {
        return ValidateResult.MBTI_FAULT
    }
    return ValidateResult.SUCCESS
}

enum class ValidateResult(val msg: String) {
    SUCCESS("success"),
    ID_FAULT("ID 는 6~10 글자여야 합니다!"),
    PW_FAULT("PW 는 8~12 글자여야 합니다!"),
    NICKNAME_FAULT("nickname 은 한 글자 이상, 공백으로만 이루어지면 안됩니다!"),
    MBTI_FAULT("MBTI 는 4글자 영어이어야 합니다!")
}