package com.example.myapplication

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hide the system bars
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (
                    window.decorView.systemUiVisibility or
                            android.view.View.SYSTEM_UI_FLAG_FULLSCREEN or
                            android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                            android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }

        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    BoxGrid(screenWidth = resources.displayMetrics.widthPixels.dp, screenHeight = resources.displayMetrics.heightPixels.dp)
                }
            }
        }
    }
}

@Composable
fun BoxGrid(screenWidth: Dp, screenHeight: Dp) {
    val boxCount = 10
    val heightPerBox = with(LocalDensity.current) { screenHeight / boxCount }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        content = {
            items(boxCount) { index ->
                Greeting(
                    "Android",
                    modifier = Modifier
                        .height(heightPerBox)
                        .fillMaxWidth()
                        .padding(top = if (index != 0) 2.dp else 0.dp)
                )
            }
        }
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var currentColor by remember { mutableStateOf(randomColor()) }

    Surface(
        modifier = modifier,
        color = currentColor,
        onClick = {
            currentColor = randomColor()
        }
    ) {
        Text(
            text = "",
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        BoxGrid(screenWidth = 360.dp, screenHeight = 640.dp) // Provide sample values for preview
    }
}

fun randomColor(): Color {
    return Color(
        red = Random.nextInt(256),
        green = Random.nextInt(256),
        blue = Random.nextInt(256)
    )
}
