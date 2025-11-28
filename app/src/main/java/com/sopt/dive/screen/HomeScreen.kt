package com.sopt.dive.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.R
import com.sopt.dive.component.AnimationBox
import com.sopt.dive.model.FlipDirection
import com.sopt.dive.model.RotationAxis
import com.sopt.dive.viewModel.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.cardState.collectAsState()

    val targetAxis = when (uiState.lastFlipDirection) {
        FlipDirection.LEFT, FlipDirection.RIGHT -> RotationAxis.Y
        FlipDirection.UP, FlipDirection.DOWN -> RotationAxis.X
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimationBox(
            front = R.drawable.ic_launcher_background,
            back = R.drawable.ic_launcher_foreground,
            targetState = uiState.currentCardState.next,
            axis = targetAxis,
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(50.dp))

        // 2. 컨트롤러 영역 (십자 버튼 배치)
        Text(text = "Flip Controller", modifier = Modifier.padding(bottom = 16.dp))

        DirectionController(
            onFlip = { direction -> viewModel.flipCard(direction) }
        )
    }
}

@Composable
fun DirectionController(
    onFlip: (FlipDirection) -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        DirectionButton(
            icon = Icons.Default.KeyboardArrowUp,
            text = "Up",
            onClick = { onFlip(FlipDirection.UP) }
        )

        Row(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            DirectionButton(
                icon = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                text = "Left",
                onClick = { onFlip(FlipDirection.LEFT) }
            )

            DirectionButton(
                icon = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                text = "Right",
                onClick = { onFlip(FlipDirection.RIGHT) }
            )
        }

        DirectionButton(
            icon = Icons.Default.KeyboardArrowDown,
            text = "Down",
            onClick = { onFlip(FlipDirection.DOWN) }
        )
    }
}

@Composable
fun DirectionButton(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(width = 100.dp, height = 50.dp)
    ) {
        Icon(imageVector = icon, contentDescription = text)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text)
    }
}