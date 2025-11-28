package com.sopt.dive.component

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
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
import com.sopt.dive.model.CardState
import com.sopt.dive.model.RotationAxis

@Preview(showBackground = true)
@Composable
fun TestAnimationBox() {
    var targetState by remember { mutableStateOf(CardState.BACK) }
    var axis by remember { mutableStateOf(RotationAxis.Y) }

    AnimationBox(
        front = R.drawable.image,
        back = R.drawable.sample_img,
        targetState = targetState,
        axis = axis,
        modifier = Modifier.size(200.dp)
    )
    Button(
        onClick = {
            targetState = if(targetState == CardState.BACK) {
                CardState.FRONT
            } else {
                CardState.BACK
            }

            axis = if(axis == RotationAxis.X) {
                RotationAxis.Y
            }
            else {
                RotationAxis.X
            }
        }
    ) { Text("Click") }
}

@Composable
fun AnimationBox(@DrawableRes front: Int, @DrawableRes back: Int,
                 targetState: CardState,
                 axis: RotationAxis,
                 modifier: Modifier = Modifier) {
    val transition = updateTransition(targetState = targetState, label = "FlipTransition")
    val rotation by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 800, easing = LinearOutSlowInEasing) },
        label = "rotation"
    ) { face ->
        face.angle
    }
    val isFrontVisible = rotation <= 90f

    Box(modifier = modifier) {
        Image(
            painter = painterResource(front),
            contentDescription = "front",
            modifier = Modifier.fillMaxSize()
                .graphicsLayer {
                    if (axis == RotationAxis.X) {
                        rotationX = rotation
                    } else {
                        rotationY = rotation
                    }
                    alpha = if (isFrontVisible) 1f else 0f
                    cameraDistance = 12f * density
                }
        )
        Image(painter = painterResource(back),
            contentDescription = "back",
            modifier = Modifier.fillMaxSize()
                .graphicsLayer {
                    if (axis == RotationAxis.X) {
                        rotationX = rotation + 180f
                    } else {
                        rotationY = rotation + 180f
                    }
                    alpha = if (isFrontVisible) 0f else 1f
                    cameraDistance = 12f * density
                })
    }
}