package au.swin.compose_jetpack

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import au.swin.compose_jetpack.components.DefaultSnackbar
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposejetpackTheme {
                Scaffold(
                    topBar = { SetAppBar() },
                    content = {
                        NameCard(name = "JakeSiewJK64");
                    },
                    bottomBar = { SetBottomBar(this) }
                )
            }
        }
    }
}

@Composable
fun SetBottomBar(context: Context) {
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
fun SetAppBar() {
    TopAppBar(
        title = { Text(text = "Therapist4u") },
        backgroundColor = Color(0xffCe67e22)
    );
}

@Composable
fun NameCard(name: String) {

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Column(modifier = Modifier.fillMaxHeight()) {
        Card(
            elevation = 10.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Column(modifier = Modifier.padding(5.dp)) {
                ArtistCard();
                Greeting(name = name)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "Process Completed!",
                                    actionLabel = "OK"
                                )
                            }
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffe74c3c))
                    ) {
                        Text("Click Me!", color = Color.White);
                    }
                }
            }
        }
        DefaultSnackbar(
            snackbarHostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(5.dp)
        ) {
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