package com.example.contacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.contacts.ui.theme.ContactsTheme
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.fillMaxWidth
import android.view.View
import androidx.compose.ui.graphics.Color
import kotlin.random.Random
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.zIndex


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // Hide navigation bar
                        or View.SYSTEM_UI_FLAG_FULLSCREEN // Hide status bar
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY // Enable immersive mode
                )

        setContent {
            ContactsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(42,240,201)
                ) {
                    ContactListScreen()
                }
            }
        }
    }
}

// Sample data for contacts
data class Contact(val name: String)

// Sample contact list
val sampleContacts = listOf(
    Contact("新井秋谷"),
    Contact("عبد السلام"),
    Contact("李连杰"),
    Contact("박장미"),
    Contact("KLJdnskk"),
    Contact("Эдуард"),
    Contact("ሓረገ ወይን"),
    Contact("松井文藏"),
    Contact("נַחְשׁוֹן"),
    Contact("이대성"),
    Contact("أمين"),
    Contact("ሳሙኤል"),
    Contact("Софья"),
    Contact("菊地玄造"),
    Contact("Тимофей"),
    Contact("X;annndsw"),
    Contact("רְפָאֵל"),
    Contact("AAAABCV"),
    Contact("הֶערשֶׁעל"),
    Contact("حبیب الرحمان"),
    Contact("大野秀満"),
    Contact("ASDjjjs"),
    Contact("남은영"),
    Contact("成龙"),
    Contact("ወርቅነህ"),
    Contact("VCx,,,smaaa"),
    Contact("村田有岡"),
    Contact("Геннадий"),
    Contact("배은서"),
    Contact("黄鹤楼"),
    Contact("דְּבוֹרָה"),
    Contact("zxczc"),
    Contact("عصمة الدين"),
    Contact("aaaaaaaaaaaaaa"),
    Contact("宮本"),
    Contact("Дмитрий"),
    Contact("لطف الرحمن"),
    Contact("배지후"),
    Contact("Mom"),
    Contact("酒井"),
    Contact("苑博"),
    Contact("ታምራት"),
    Contact("Ростислав"),
    Contact("xcxcxc"),
    Contact("شهاب الدین"),
    Contact("海云"),
    Contact("הֶבֶל"),
    Contact("小野")
)

@Composable
fun ContactListScreen() {
    var selectedContact by remember { mutableStateOf<Contact?>(null) }

    Column(modifier = Modifier.fillMaxSize()) {
        if (selectedContact != null) {
            DisplaySingleImage()
        } else {
            ContactList(contacts = sampleContacts) { contact ->
                selectedContact = contact
            }
        }
    }
}

@Composable
fun ContactList(contacts: List<Contact>, onContactClick: (Contact) -> Unit) {
    val lightShade = Color(250, 196, 255) // Light gray shade
    val darkShade = Color(255, 255, 183) // Darker gray shade

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(contacts) { contact ->
            // Determine the background color based on the position in the list
            val backgroundColor = if (contacts.indexOf(contact) % 2 == 0) lightShade else darkShade

            Text(
                text = contact.name,
                fontSize = 35.sp,
                modifier = Modifier
                    .clickable { onContactClick(contact) }
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(backgroundColor) // Apply background color here
            )
        }
    }
}


@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name!",
        modifier = Modifier.padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun ContactListPreview() {
    ContactsTheme {
        ContactListScreen()
    }
}

private fun getRandomColor(): Color {
    val random = Random.Default

    return Color(
        random.nextInt(256) / 255f,
        random.nextInt(256) / 255f,
        random.nextInt(256) / 255f
    )

}

@Composable
fun DisplaySingleImage() {
    var answerClicked by remember { mutableStateOf(false) }
    var decClicked by remember { mutableStateOf(false) }

    // Specify the resource ID of the image saved in the drawable folder
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(    if (answerClicked) {
                Color(240, 201, 42) // Set color to yellow if answer picture is clicked
            } else if (!answerClicked && decClicked) {
                Color(0, 0, 0) // Set color to black if declinewhite picture is clicked

            } else {
                Color.Transparent // Set color to transparent otherwise
            }) // Change background color to yellow if answer picture is clicked
            .absoluteOffset(x = 40.dp, y = 600.dp)
    ) {
        // Display answer image and handle its click
        if (!answerClicked && !decClicked) {
            Image(
                painter = painterResource(id = R.drawable.answer),
                contentDescription = null, // No content description for decorative image
                modifier = Modifier
                    .size(width = 80.dp, height = 80.dp)
                    .aspectRatio(1f, matchHeightConstraintsFirst = true)
                    .alpha(1f)
                    .zIndex(5f) // Set higher z-index to overlay over the background
                    .clickable {
                        answerClicked = true // Set the state to true when the answer picture is clicked
                    }
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (answerClicked) Color.Red else Color.Transparent) // Change background color to red if answer picture is clicked
            .absoluteOffset(x = 230.dp, y = -100.dp)
    ) {
        // Display answer image and handle its click
        if (!answerClicked && !decClicked) {
            Image(
                painter = painterResource(id = R.drawable.textbub),
                contentDescription = null, // No content description for decorative image
                modifier = Modifier
                    .size(width = 100.dp, height = 100.dp)
                    .aspectRatio(1f, matchHeightConstraintsFirst = true)
                    .alpha(0.8f)
                    .zIndex(5f) // Set higher z-index to overlay over the background
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (answerClicked) Color.Red else Color.Transparent) // Change background color to red if answer picture is clicked
            .absoluteOffset(x = 0.dp, y = -465.dp)
    ) {
        // Display answer image and handle its click
        if (!answerClicked && !decClicked) {
            Image(
                painter = painterResource(id = R.drawable.mom),
                contentDescription = null, // No content description for decorative image
                modifier = Modifier
                    .size(width = 2000.dp, height = 2000.dp) // Customize size as needed
                    .aspectRatio(
                        0.5f,
                        matchHeightConstraintsFirst = true
                    ) // Set the desired aspect ratio
                    .alpha(0.8f)
                    .zIndex(5f) // Set higher z-index to overlay over the background
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (answerClicked) Color.Red else Color.Transparent) // Change background color to red if answer picture is clicked
            .absoluteOffset(x = 0.dp, y = -595.dp)
    ) {
        // Display answer image and handle its click
        if (answerClicked) {
            Image(
                painter = painterResource(id = R.drawable.momtonly),
                contentDescription = null, // No content description for decorative image
                modifier = Modifier
                    .size(width = 8000.dp, height = 8000.dp) // Customize size as needed
                    .aspectRatio(
                        2f
                    ) // Set the desired aspect ratio
                    .alpha(0.8f)
                    .zIndex(5f) // Set higher z-index to overlay over the background
            )
        }
    }

    if (decClicked) {
        // Display a black background when decClicked is true
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .zIndex(1f) // Set lower z-index to be below other content
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (answerClicked) Color.Red else Color.Transparent) // Change background color to red if answer picture is clicked
            .absoluteOffset(x = 130.dp, y = -100.dp)
    ) {
        // Display declinewhite image and handle its click
        if (answerClicked) {
            Image(
                painter = painterResource(id = R.drawable.declinewhite),
                contentDescription = null, // No content description for decorative image
                modifier = Modifier
                    .size(width = 80.dp, height = 80.dp)
                    .aspectRatio(1f, matchHeightConstraintsFirst = true)
                    .alpha(0.8f)
                    .zIndex(5f) // Set higher z-index to overlay over the background
                    .clickable {
                        decClicked = true // Set the state to true when the declinewhite picture is clicked
                        answerClicked = false // Set answerClicked to false to reset the background and image transparency
                    }
            )
        }
    }
}

