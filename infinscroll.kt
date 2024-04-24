package com.example.scrollinfin

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.scrollinfin.ui.theme.ScrollinfinTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // Hide navigation bar
                        or View.SYSTEM_UI_FLAG_FULLSCREEN // Hide status bar
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY // Enable immersive mode
                )

        setContent {
            ScrollinfinTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    InfiniteScrollingRectangles()
                }
            }
        }
    }
}

@Composable
fun InfiniteScrollingRectangles() {
    val listState = rememberLazyListState()
    val itemCount = 1000 // Adjust the number of rectangles

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed((0 until itemCount).toList()) { index, _ ->
            val random = Random.Default
            val color = Color(
                random.nextInt(256) / 255f,
                random.nextInt(256) / 255f,
                random.nextInt(256) / 255f
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .height(800.dp)
                    .background(color)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InfiniteScrollingRectanglesPreview() {
    ScrollinfinTheme {
        InfiniteScrollingRectangles()
    }
}
