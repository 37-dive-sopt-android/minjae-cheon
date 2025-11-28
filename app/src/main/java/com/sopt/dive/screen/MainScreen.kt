package com.sopt.dive.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sopt.dive.R
import com.sopt.dive.page.MyPage
import com.sopt.dive.viewModel.MainViewModel
import com.sopt.dive.viewModel.UserInfoStatus

@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel(),
) {
    val userInfoStatus = viewModel.userInfo.collectAsState()
    when(val state = userInfoStatus.value) {
        UserInfoStatus.NeedPending -> Text("Loading...")
        is UserInfoStatus.Ready -> {
            val userInfo = state.value
            MyPage(
                username = userInfo.username,
                pw = userInfo.pw,
                name = userInfo.name,
                email = userInfo.email,
                age = userInfo.age,
                imgId = R.drawable.image, // 미구현 임시 이미지
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}