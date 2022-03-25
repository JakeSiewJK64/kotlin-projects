package au.swin.compose_jetpack

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.service.autofill.OnClickAction
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.Modifier

import au.swin.compose_jetpack.ui.theme.ComposejetpackTheme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposejetpackTheme {
                Scaffold(
                    topBar = { setAppBar() },
                    content = {
                        NameCard(name = "JakeSiewJK64");
                    },
                    bottomBar = { setBottomBar(this) }
                )
            }
        }
    }
}

@Composable
fun setBottomBar(context: Context) {
    val selectedIndex = remember { mutableStateOf(0) }
    BottomNavigation(elevation = 10.dp) {
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Home, "") },
            selected = (selectedIndex.value == 0),
            onClick = { selectedIndex.value = 0 });
        BottomNavigationItem(
            icon = { Icon(Icons.Default.MailOutline, "") },
            selected = (selectedIndex.value == 1),
            onClick = { selectedIndex.value = 1 });
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Person, "") },
            selected = (selectedIndex.value == 2),
            onClick = {
                selectedIndex.value = 2;
                Toast.makeText(context, "Profile", Toast.LENGTH_SHORT).show();
            });
    }
}

@SuppressLint("InvalidColorHexValue")
@Composable
fun setAppBar() {
    TopAppBar(
        title = { Text(text = "Therapist4u") },
        backgroundColor = Color(0xffCe67e22)
    );
}

@Composable
fun NameCard(name: String) {
    Card(
        elevation = 10.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { }) {
        Box(modifier = Modifier.padding(5.dp)) {
            Column {
                ArtistCard();
                Greeting(name = name)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffe74c3c))
                    ) {
                        Text("Click Me!", color = Color.White);
                    }
                }
            }
        }
    }
}

@Composable
fun ArtistCard() {
    Text("Hello World", fontSize = 50.sp, fontWeight = FontWeight.Bold)
    Text("3 minutes ago")
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposejetpackTheme {
        Greeting("Android")
    }
}