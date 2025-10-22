package com.sopt.dive.activity

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.UserInfo
import com.sopt.dive.component.CustomButton
import com.sopt.dive.component.Header
import com.sopt.dive.component.LoginField
import com.sopt.dive.component.SignUpButton
import com.sopt.dive.ui.theme.DiveTheme

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiveTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginPage(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPage(modifier: Modifier = Modifier) {
    var savedInfo by remember { mutableStateOf(UserInfo(Intent())) }

    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    val signUpLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            savedInfo = UserInfo(data)
        }
    }

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
                if (id != "" && pw != "" && savedInfo.validate(id, pw)) {
                    val intent = Intent(context, MainPageActivity::class.java)
                    savedInfo.setIntent(intent)
                    context.startActivity(intent)
                } else {
                    Toast.makeText(context, "로그인 실패", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        SignUpButton({
            val intent = Intent(context, SignUpActivity::class.java)
            signUpLauncher.launch(intent)
        })

        Spacer(Modifier.height(5.dp))
    }
}
