package com.example.juricass.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Pill(text: String, modifier: Modifier = Modifier) {
    Box(modifier = Modifier.padding(4.dp)) {
        Box(
            modifier = modifier
                .background(MaterialTheme.colors.secondary, RoundedCornerShape(16.dp))
                .padding(horizontal = 10.dp, vertical = 4.dp)
        ) {
            Text(text = text, color = MaterialTheme.colors.onSecondary, fontSize = 16.sp)
        }
    }
}


@Preview("Light Theme", showBackground = true, showSystemUi = true)
@Composable
fun PillPreview() {
    Row() {
        Pill(
            text = "Hello World",
        )
    }
}
