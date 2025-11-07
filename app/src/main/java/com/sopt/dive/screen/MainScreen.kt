package com.sopt.dive.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.sopt.dive.R
import com.sopt.dive.page.MyPage
import com.sopt.dive.viewModel.MainViewModel
import com.sopt.dive.viewModel.UserInfoStatus

@Composable
fun MainScreen(
    viewModel: MainViewModel,
) {
    val userInfoStatus = viewModel.userInfo.collectAsState()
    when(val state = userInfoStatus.value) {
        UserInfoStatus.NeedPending -> Text("Loading...")
        is UserInfoStatus.Ready -> {
            val userInfo = state.value
            MyPage(
                id = userInfo.id,
                pw = userInfo.pw,
                nickname = userInfo.nickname,
                mbti = userInfo.mbti,
                imgId = R.drawable.image, // 미구현 임시 이미지
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}