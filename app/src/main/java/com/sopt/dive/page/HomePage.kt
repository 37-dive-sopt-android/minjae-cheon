package com.sopt.dive.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sopt.dive.R
import com.sopt.dive.component.AnimationBox
import com.sopt.dive.model.CardState
import com.sopt.dive.model.RotationAxis

@Composable
fun HomePage(
    targetState: CardState,
    axis: RotationAxis,
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Spacer(Modifier.padding(100.dp))

        AnimationBox(
            front = R.drawable.image,
            back = R.drawable.sample_img,
            targetState = targetState,
            axis = axis,
            modifier = Modifier.size(300.dp)
        )
    }
}