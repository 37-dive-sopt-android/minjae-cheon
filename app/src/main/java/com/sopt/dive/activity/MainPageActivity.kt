package com.sopt.dive.activity

import android.icu.number.Scale
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sopt.dive.R
import com.sopt.dive.component.Info
import com.sopt.dive.component.SimpleIntroduction
import com.sopt.dive.ui.theme.DiveTheme

class MainPageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val intent = intent
        val id = intent.getStringExtra("ID") ?: ""
        val pw = intent.getStringExtra("PW") ?: ""
        val nickname = intent.getStringExtra("NICKNAME") ?: ""
        val mbti = intent.getStringExtra("MBTI") ?: ""

        setContent {
            DiveTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainPage(
                        id, pw, nickname, mbti,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestMainPage() {
    MainPage("11", "22", "33", "44", modifier = Modifier.fillMaxSize())
}

@Composable
fun MainPage(id: String, pw: String, nickname: String, mbti: String,
    modifier: Modifier = Modifier) {
    val img = painterResource(id = R.drawable.image)

    Column(modifier = modifier.fillMaxWidth().padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)) {
        SimpleIntroduction(img, "자기소개 이미지입니다",
            nickname, "$nickname 입니다 반갑습니다")

        Spacer(Modifier.padding(20.dp))

        Info("ID", id)
        Info("PW", pw)
        Info("NICKNAME", nickname)
        Info("MBTI", mbti)
    }
}
