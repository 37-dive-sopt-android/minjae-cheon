package com.sopt.dive.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.unit.dp
import com.sopt.dive.UserUIInfo

@Composable
fun SignUpField(
    userUIInfo: List<UserUIInfo>,
    focusManager: FocusManager,
    modifier: Modifier = Modifier) {
    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        for (userUI in userUIInfo) {
            Field(
                text = userUI.text,
                label = userUI.label,
                value = userUI.value,
                onValueChange = userUI.onChange,
                inputVisibility = userUI.inputVisibility,
                focusManager = focusManager,
            )
        }
    }
}