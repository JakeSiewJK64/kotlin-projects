package au.swin.compose_jetpack.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun DefaultSnackbar(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier,
    onDismiss: () -> Unit
) {
    SnackbarHost(
        hostState = snackbarHostState,
        snackbar = { data ->
            Snackbar(
                modifier = modifier,
                action = {
                    data.actionLabel?.let { actionLabel ->
                        TextButton(onClick = { /*TODO*/ }) {
                            Text(
                                text = actionLabel,
                                style = MaterialTheme.typography.body2,
                                color = Color.White
                            )
                        }
                    }
                },
                content = {
                    Text(data.message)
                }
            )
        },
        modifier = modifier
    )
}