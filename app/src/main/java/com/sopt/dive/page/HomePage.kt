package com.sopt.dive.page

import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sopt.dive.R
import com.sopt.dive.component.AnimationBox

@Composable
fun HomePage(modifier: Modifier = Modifier) {
    var isFlipped by remember { mutableStateOf(false) }

    Column(modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Spacer(Modifier.padding(100.dp))

        AnimationBox(R.drawable.image, R.drawable.sample_img,
            isFlipped = isFlipped, Modifier.size(300.dp))

        Spacer(Modifier.weight(1f))

        Button(
            onClick = {
                isFlipped = !isFlipped
            }
        ) {
            Text("click to change card")
        }
        Spacer(Modifier.padding(20.dp))
    }
}