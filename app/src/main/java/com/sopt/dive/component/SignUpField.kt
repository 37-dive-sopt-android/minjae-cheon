package com.sopt.dive.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SignUpField(
    id: String, onChangeId: (String) -> Unit,
    pw: String, onChangePw: (String) -> Unit,
    nickname: String, onChangeNickname: (String) -> Unit,
    mbti: String, onChangeMbti: (String) -> Unit,
    modifier: Modifier = Modifier) {
    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Field("ID", "아이디를 입력해주세요",
            id, onChangeId, true)
        Field("PW", "비밀번호를 입력해주세요",
            pw, onChangePw, false)
        Field("NICKNAME", "닉네임을 입력해주세요",
            nickname, onChangeNickname, true)
        Field("MBTI", "MBTI 를 입력해주세요",
            mbti, onChangeMbti, true)
    }
}