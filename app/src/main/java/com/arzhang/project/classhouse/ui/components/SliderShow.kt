package com.arzhang.project.classhouse.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arzhang.project.classhouse.R
import com.arzhang.project.classhouse.ui.theme.ClassHouseTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Slider() {
    val imageList = listOf(R.drawable.python,R.drawable.database)
    val state = rememberPagerState { imageList.size }
    var key by remember { mutableStateOf(false) }
    HorizontalPager(state = state, beyondBoundsPageCount = 1) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = imageList[it]),
                contentDescription = null,
                Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }

    LaunchedEffect(key1 = key) {
        launch {
            delay(2000)
            with(state) {
                val target = if (currentPage < imageList.size - 1) currentPage + 1 else 0
                animateScrollToPage(
                    target,
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = FastOutSlowInEasing
                    )
                )
                key = !key
            }
        }
    }
}

@Preview(device = "spec:width=411dp,height=891dp")
@Composable
fun SliderPreview() {
    ClassHouseTheme {
        Slider()
    }
}
