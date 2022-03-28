package au.swin.compose_jetpack.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@SuppressLint("InvalidColorHexValue")
@Preview
@Composable
fun LoginPage() {

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        var email by remember { mutableStateOf("") }
        Card(
            Modifier
                .weight(2f)
                .padding(8.dp),
            shape = RoundedCornerShape(32.dp)
        ) {
            Column {
                Text(text = "Login Screen", fontSize = 32.sp)
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { it ->
                        email = it
                    },
                    placeholder = {
                        Text("Email")
                    },
                    label = {
                        Text("Email")
                    }
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = { it ->
                        email = it
                    },
                    placeholder = {
                        Text("Password")
                    },
                    label = {
                        Text("Password")
                    }
                )
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(Color(0xfff1c40f)),
                    modifier = Modifier.padding(5.dp),
                    elevation = null
                ) {
                    Text("Login")
                }
            }
        }
    }
}