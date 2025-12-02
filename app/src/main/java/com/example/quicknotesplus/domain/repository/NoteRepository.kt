package com.example.quicknotesplus.domain.repository

import com.example.quicknotesplus.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNotes(): Flow<List<Note>>

    suspend fun getNoteDetails(id: Int): Note?

    suspend fun addNote(note: Note)

    suspend fun deleteNote(note: Note)
}