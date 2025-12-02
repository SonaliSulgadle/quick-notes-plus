package com.example.quicknotesplus.ui.note_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.example.quicknotesplus.domain.model.Note
import com.example.quicknotesplus.ui.note_list.note
import com.example.quicknotesplus.ui.theme.QuickNotesPlusTheme
import com.example.quicknotesplus.ui.theme.padding_20
import com.example.quicknotesplus.ui.theme.padding_6
import com.example.quicknotesplus.ui.theme.padding_8
import com.example.quicknotesplus.ui.util.formatRelativeTime

@Composable
fun NoteItem(
    note: Note,
    onClick: (Int) -> Unit,
    modifier: Modifier,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = modifier,
        onClick = { onClick(note.id) }
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(padding_8)
        ) {
            Column {
                Text(
                    text = note.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.padding(padding_8))
                Text(
                    text = note.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 10
                )
                Spacer(modifier = Modifier.padding(padding_6))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = formatRelativeTime(note.timestamp),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                    IconButton(
                        modifier = Modifier.size(padding_20),
                        onClick = onDeleteClick

                    ) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                    }
                }

            }
        }
    }
}

@Composable
@PreviewLightDark
fun PreviewNoteItem() {
    QuickNotesPlusTheme {
        NoteItem(note, {}, Modifier, {})
    }
}