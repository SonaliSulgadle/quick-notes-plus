package com.example.quicknotesplus.ui.note_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quicknotesplus.R
import com.example.quicknotesplus.domain.model.Note
import com.example.quicknotesplus.ui.note_list.components.NoteItem
import com.example.quicknotesplus.ui.theme.QuickNotesPlusTheme
import com.example.quicknotesplus.ui.theme.largeTextSize
import com.example.quicknotesplus.ui.theme.padding_16

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteListScreen(
    viewModel: NoteListViewModel = hiltViewModel(),
    onClick: (Int) -> Unit
) {

    val notes by viewModel.state.collectAsState()

    LaunchedEffect(notes) {
        println("NOTES EMITTED -> ${notes.size}")
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.notes_screen))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onClick(-1)
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },
        content = { paddingValues ->
            if (notes.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.height(padding_16))
                    Text(
                        text = stringResource(R.string.notes_empty_message),
                        textAlign = TextAlign.Center,
                        fontSize = largeTextSize,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                }
            } else {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = paddingValues
                ) {
                    items(notes, key = { note -> note.id }) { note ->
                        NoteItem(
                            note = note,
                            onClick = { onClick(note.id) },
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            onDeleteClick = {
                                viewModel.onDeleteClick(note)
                            }
                        )
                    }
                }
            }
        }
    )
}

@Composable
@PreviewLightDark
fun PreviewNoteListScreen() {
    QuickNotesPlusTheme {
        NoteListScreen(onClick = {})
    }
}

internal val note = Note(
    id = 1,
    title = "Title 1",
    content = "Content 1",
    timestamp = 0L
)

internal val notesList = listOf(
    Note(
        id = 1,
        title = "Title 1",
        content = "Content 1",
        timestamp = 0L
    ),
    Note(
        id = 2,
        title = "Title 2",
        content = "Content 2",
        timestamp = 0L
    ),
    Note(
        id = 3,
        title = "Title 3",
        content = "Content 3",
        timestamp = 0L
    ),
    Note(
        id = 4,
        title = "Title 4",
        content = "Content 4",
        timestamp = 0L
    ),
)
