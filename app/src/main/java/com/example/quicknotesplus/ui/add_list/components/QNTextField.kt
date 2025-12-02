package com.example.quicknotesplus.ui.add_list.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.example.quicknotesplus.ui.theme.QuickNotesPlusTheme

@Composable
fun QNTextField(
    text: String,
    textStyle: TextStyle,
    singleLine: Boolean = false,
    placeholder: String,
    onValueChange: (String) -> Unit,
    onFocusChanged: (FocusState) -> Unit,
    modifier: Modifier = Modifier

) {
    Box(modifier = modifier) {
        TextField(
            value = text,
            onValueChange = onValueChange,
            textStyle = textStyle,
            placeholder = { Text(placeholder) },
            singleLine = singleLine,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { onFocusChanged(it) },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            )
        )
    }
}

@Composable
@PreviewLightDark
fun previewQNTextField() {
    QuickNotesPlusTheme() {
        QNTextField(
            text = "Text",
            textStyle = TextStyle(),
            placeholder = "Title",
            onValueChange = {},
            onFocusChanged = {}
        )
    }
}