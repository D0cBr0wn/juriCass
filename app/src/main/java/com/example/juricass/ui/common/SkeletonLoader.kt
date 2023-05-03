package com.example.juricass.ui.common

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.juricass.ui.theme.JuriCassTheme


@Composable
fun SkeletonLoader(isLoading: Boolean = false, rows: Int = 10, error: String? = null) {
    if(isLoading || error !== null) {
        Column(
            verticalArrangement = if(error === null) Arrangement.Top else Arrangement.Center,
            modifier = Modifier.fillMaxSize().testTag("skeletonLoader")) {
            if(error === null) {
                for (i in 1..rows) {
                    SkeletonRow()
                }
            } else {
                Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                    ErrorDisplayer(error)
                }

            }
        }
    }
}

@Composable
fun SkeletonRow() {
    Surface(elevation =1.dp, modifier = Modifier.padding(4.dp)) {
        Row(modifier = Modifier.padding(8.dp)) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(20.dp)
                        .shimmerEffect()
                )
            }
        }
    }
}

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }

    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable( tween(1000))
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFB8B5B5),
                Color(0xFF8F8B8B),
                Color(0xFFB8B5B5)
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        ),
    )
        .onGloballyPositioned {
            size = it.size
        }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = true, showSystemUi = true)
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, showSystemUi = true)
@Composable
fun SkeletonLoaderPreview() {
    JuriCassTheme() {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            SkeletonLoader(isLoading = true)
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = true, showSystemUi = true)
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, showSystemUi = true)
@Composable
fun SkeletonLoaderWithErrorPreview() {
    JuriCassTheme() {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            SkeletonLoader(isLoading= false, error = "An error occured")
        }
    }
}