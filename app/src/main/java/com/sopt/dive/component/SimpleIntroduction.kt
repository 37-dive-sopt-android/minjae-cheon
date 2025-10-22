package com.sopt.dive.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R

@Preview(showBackground = true)
@Composable
fun TestSimple() {
    SimpleIntroduction(img = painterResource(id = R.drawable.image), imgDescription = "blah-blah",
        nickname = "그래그래", shortIntroduction = "그래그래 이구나")
}

@Composable
fun SimpleIntroduction(img: Painter, imgDescription: String, nickname: String, shortIntroduction: String,
                       modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = img, imgDescription,
                modifier = Modifier.size(50.dp)
            )
            Text(
                nickname, fontSize = 20.sp, fontWeight = W600,
                modifier = Modifier.padding(10.dp)
            )
        }
        Text(shortIntroduction)
    }
}
