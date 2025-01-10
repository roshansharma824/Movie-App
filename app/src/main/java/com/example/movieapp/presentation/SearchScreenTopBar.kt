package com.example.movieapp.presentation
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun SearchScreenTopBar(

    onQueryChanged: (String) -> Unit,
    onClearQuery: () ->Unit
) {
    var searchInput by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        TopAppBar(
            backgroundColor = Color.Transparent,
            contentColor = Color.Transparent,
            actions = {
                IconButton(onClick = {
                    searchInput = ""
                    onClearQuery.invoke()
                }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Close Icon",
                        tint = Color.White,
                        modifier = Modifier.size(
                            20.dp
                        )
                    )
                }
            },
            elevation = 0.dp,
            title = {
                SearchTextField(text = searchInput, placeholderText = "Search yours notes") { newText ->
                    searchInput = newText
                    onQueryChanged(searchInput)
                }
            },
            modifier = Modifier
                .background(
                    color = Color.Gray,
                    shape = RoundedCornerShape(50)
                )
                .padding(horizontal = 8.dp),
            navigationIcon = {

            }
        )
    }
}

@Composable
fun SearchTextField(
    text: String,
    placeholderText: String,
    onTextChanged: (String) -> Unit
) {
    var isKeyboardVisible by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    TextField(
        value = text,
        onValueChange = {
            onTextChanged(it)
        },
        placeholder = {
            androidx.compose.material3.Text(
                text = placeholderText,
                style = MaterialTheme.typography.titleMedium.copy(color = Color.Gray.copy(alpha = 0.5f), fontSize = 18.sp, fontWeight = FontWeight.W300),
            )
        },
        textStyle = MaterialTheme.typography.titleMedium.copy(color = Color.Gray.copy(alpha = 0.5f), fontSize = 18.sp, fontWeight = FontWeight.W300),
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .onFocusChanged { isKeyboardVisible = true },

        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                isKeyboardVisible = false
            }
        )
    )
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    DisposableEffect(Unit) {
        onDispose {
            // Cleanup, e.g., close keyboard when the composable is disposed
            if (isKeyboardVisible) {
                // Close keyboard
                isKeyboardVisible = false
            }
        }
    }
}