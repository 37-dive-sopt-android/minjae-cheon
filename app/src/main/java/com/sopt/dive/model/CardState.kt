package com.sopt.dive.model

enum class RotationAxis {
    X, Y
}
enum class FlipDirection(val axis: RotationAxis) {
    LEFT(RotationAxis.Y),
    RIGHT(RotationAxis.Y),
    UP(RotationAxis.X),
    DOWN(RotationAxis.X)
}

enum class CardState(val angle: Float) {
    FRONT(0f),
    BACK(180f);

    val next: CardState
        get() = if (this == FRONT) BACK else FRONT
}