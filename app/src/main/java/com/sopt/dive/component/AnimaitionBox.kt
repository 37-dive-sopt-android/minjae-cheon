package com.sopt.dive.component

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.R

@Preview(showBackground = true)
@Composable
fun TestAnimationBox() {
    var isFlipped by remember { mutableStateOf(false) }

    AnimationBox(
        front = R.drawable.image,
        back = R.drawable.sample_img,
        isFlipped = isFlipped,
        modifier = Modifier.size(200.dp)
    )
    Button(
        onClick = { isFlipped = !isFlipped }
    ) { Text("Click") }
}

@Composable
fun AnimationBox(@DrawableRes front: Int, @DrawableRes back: Int,
                 isFlipped: Boolean,
                 modifier: Modifier = Modifier) {
    val animation = updateTransition(isFlipped)
    val rotate by animation.animateFloat(
        transitionSpec = {
            tween(durationMillis = 1200, easing = LinearOutSlowInEasing)
        },
        label = "rotate") { state ->
        when(state) {
            true -> 180f
            false -> 0f
        }
    }
    val alphaValue by animation.animateFloat(
        transitionSpec = {
            tween(durationMillis = 1200, easing = LinearOutSlowInEasing)
        },
        label = "alpha") { state ->
        when(state) {
            true -> 0f
            false -> 1f
        }
    }

    Box(modifier = modifier) {
        Image(
            painter = painterResource(front),
            contentDescription = "front",
            modifier = Modifier.fillMaxSize()
                .graphicsLayer {
                    rotationY = rotate
                    alpha = alphaValue
                    cameraDistance = 12f * density
                }
        )
        Image(painter = painterResource(back),
            contentDescription = "back",
            modifier = Modifier.fillMaxSize()
                .graphicsLayer {
                    rotationY = rotate + 180f
                    alpha = 1f - alphaValue
                    cameraDistance = 12f * density
                })
    }
}