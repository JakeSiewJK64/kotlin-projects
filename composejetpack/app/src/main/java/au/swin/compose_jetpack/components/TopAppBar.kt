package au.swin.compose_jetpack.components

import android.annotation.SuppressLint
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@SuppressLint("InvalidColorHexValue")
@Composable
fun SetAppBar() {
    TopAppBar(
        title = { Text(text = "Therapist4u") },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.Default.MoreVert, "")
            }
        },
        backgroundColor = Color(0xffCe67e22),
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Home, "");
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Person, "");
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.AccountBox, "");
            }
        }
    );
}