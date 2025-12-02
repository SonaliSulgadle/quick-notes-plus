package com.example.quicknotesplus.ui.add_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quicknotesplus.R
import com.example.quicknotesplus.ui.add_list.AddEditViewModel.Companion.NOTES_EMPTY
import com.example.quicknotesplus.ui.add_list.components.QNTextField
import com.example.quicknotesplus.ui.theme.padding_16

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditNoteScreen(
    noteId: Int,
    viewModel: AddEditViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val state = viewModel.state.value

    LaunchedEffect(state.isSaved) {
        if (state.isSaved) {
            onNavigateBack()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (noteId == NOTES_EMPTY) stringResource(R.string.add_note_title)
                        else stringResource(R.string.edit_note_title),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.content_description_back)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.saveNote()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = stringResource(R.string.content_description_save)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(vertical = padding_16)
        ) {

            QNTextField(
                text = state.title,
                textStyle = MaterialTheme.typography.titleLarge,
                placeholder = stringResource(R.string.title_placeholder),
                singleLine = true,
                onValueChange = {
                    viewModel.onTitleChange(it)
                },
                onFocusChanged = {},
            )

            Spacer(modifier = Modifier.height(padding_16))

            QNTextField(
                text = state.content,
                textStyle = MaterialTheme.typography.bodyLarge,
                singleLine = false,
                placeholder = stringResource(R.string.content_placeholder),
                onValueChange = {
                    viewModel.onContentChange(it)
                },
                onFocusChanged = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }
}