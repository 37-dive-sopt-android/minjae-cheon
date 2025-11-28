package com.sopt.dive.page

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sopt.dive.R
import com.sopt.dive.component.Info
import com.sopt.dive.component.SimpleIntroduction

@Composable
fun MyPage(username: String, pw: String, name: String, email: String, age:Int, @DrawableRes imgId: Int,
             modifier: Modifier = Modifier) {
    val img = painterResource(id = imgId)

    Column(modifier = modifier.fillMaxWidth().padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)) {
        SimpleIntroduction(img, "자기소개 이미지입니다",
            name, "$name 입니다 반갑습니다")

        Spacer(Modifier.padding(20.dp))

        Info("username", username)
        Info("password", pw)
        Info("name", name)
        Info("age", age.toString())
        Info("email", email)
    }
}