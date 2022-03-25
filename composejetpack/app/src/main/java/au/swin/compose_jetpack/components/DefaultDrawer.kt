package au.swin.compose_jetpack.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DefaultDrawer(drawerState: DrawerState, modifier: Modifier) {
    ModalDrawer(
        drawerState = drawerState,
        drawerContent = {
            Column {
                Text("Text")
            }
        },
        content = {
            Column {
                Text("text")
            }
        },
        modifier = modifier
    )
}