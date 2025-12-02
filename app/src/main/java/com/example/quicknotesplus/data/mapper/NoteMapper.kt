package com.example.quicknotesplus.data.mapper

import com.example.quicknotesplus.data.local.NoteEntity
import com.example.quicknotesplus.domain.model.Note

fun List<NoteEntity>.mapToNote(): List<Note> {
    val notes = mutableListOf<Note>()

    this.forEach {
        notes.add(
            it.mapToNote()
        )
    }

    return notes
}

fun NoteEntity.mapToNote() = Note(
    id = id,
    title = title,
    content = content,
    timestamp = timestamp
)

fun Note.mapToNoteEntity(): NoteEntity {
    return NoteEntity(
        id = id,
        title = title,
        content = content,
        timestamp = timestamp
    )
}