package com.example.phonecall

import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.phonecall.ui.theme.PhonecallTheme
import kotlin.random.Random
import androidx.core.view.WindowInsetsControllerCompat
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.core.view.WindowCompat
import com.example.phonecall.ui.theme.PhonecallTheme
import android.view.View
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import com.example.phonecall.ui.theme.PhonecallTheme
import android.content.Context
import android.os.Vibrator


class MainActivity : ComponentActivity() {
    private val handler = Handler()
    private var colorUpdateRunnable: Runnable? = null
    private val updateIntervalMillis = 130L // 1 second
    private lateinit var vibrator: Vibrator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        startVibration()

        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // Hide navigation bar
                        or View.SYSTEM_UI_FLAG_FULLSCREEN // Hide status bar
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY // Enable immersive mode
                )

        setContent {
            PhonecallTheme {
                // State to keep track of the current color
                var currentColor by remember { mutableStateOf(getRandomColor()) }

                // Use a Box layout to layer the static image and the dynamic background
                Box(
                    modifier = Modifier
                        .fillMaxSize()

                ) {
                    // The dynamic background color
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = currentColor
                    ) {
                        // Greeting Composable
                        Greeting("Android")
                    }

                    // Static PNG image overlay (ensure you replace 'R.drawable.my_image' with your image resource ID)
                    Image(
                        painter = painterResource(id = R.drawable.whitebg),
                        contentDescription = null, // No content description for decorative image
                        modifier = Modifier
                            .fillMaxSize()
                            .alpha(0.6f) // Optional: Adjust the alpha for transparency
                            .zIndex(1f) // Set higher z-index to overlay over the background
                            .absoluteOffset(x = 0.dp, y = -160.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .absoluteOffset(x = 220.dp, y = 550.dp)

                ) {

                    Image(
                        painter = painterResource(id = R.drawable.declinewhite),
                        contentDescription = null, // No content description for decorative image
                        modifier = Modifier
                            .size(width = 110.dp, height = 90.dp)
                            .aspectRatio(1f, matchHeightConstraintsFirst = true)
                            .alpha(0.6f)
                            .zIndex(4f) // Set higher z-index to overlay over the background

                    )

                }



                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .absoluteOffset(x = 30.dp, y = 550.dp)
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.answer),
                        contentDescription = null, // No content description for decorative image
                        modifier = Modifier
                            .size(width = 110.dp, height = 90.dp)
                            .aspectRatio(1f, matchHeightConstraintsFirst = true)
                            .alpha(0.6f)
                            .zIndex(5f) // Set higher z-index to overlay over the background

                    )
                }




                // Start the background color update loop
                startBackgroundColorUpdateLoop { newColor ->
                    // Update the current color when the handler triggers the callback
                    currentColor = newColor

                }

            }
        }
    }

    private fun startBackgroundColorUpdateLoop(onColorChange: (Color) -> Unit) {
        // Cancel any existing color update runnable
        colorUpdateRunnable?.let {
            handler.removeCallbacks(it)
        }

        // Define a new runnable to change the background color and schedule the next update
        colorUpdateRunnable = Runnable {
            // Get a new random color and update the state
            val newColor = getRandomColor()
            onColorChange(newColor)

            // Schedule the next update
            handler.postDelayed(colorUpdateRunnable!!, updateIntervalMillis)
        }

        // Schedule the initial update
        handler.postDelayed(colorUpdateRunnable!!, updateIntervalMillis)
    }

    private fun getRandomColor(): Color {
        val random = Random.Default

        return Color(
            random.nextInt(256) / 255f,
            random.nextInt(256) / 255f,
            random.nextInt(256) / 255f
        )

    }

    private fun startVibration() {
        val vibrationPattern = longArrayOf(0, 1000, 1000) // Wait 0ms, vibrate for 1 second, wait 1 second
        vibrator.vibrate(vibrationPattern, 0) // Repeat the pattern indefinitely
    }

    private fun stopVibration() {
        vibrator.cancel()
    }

    override fun onPause() {
        super.onPause()
        stopVibration()
    }

    override fun onStop() {
        super.onStop()
        stopVibration()
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(text = " ", modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PhonecallTheme {
        Greeting("Android")
    }
}
