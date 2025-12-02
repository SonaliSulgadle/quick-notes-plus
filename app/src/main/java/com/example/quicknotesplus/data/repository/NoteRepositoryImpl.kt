package com.example.quicknotesplus.data.repository

import com.example.quicknotesplus.data.local.NoteDao
import com.example.quicknotesplus.data.mapper.mapToNote
import com.example.quicknotesplus.data.mapper.mapToNoteEntity
import com.example.quicknotesplus.domain.model.Note
import com.example.quicknotesplus.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return noteDao.getNotes().map { it.mapToNote() }
    }

    override suspend fun getNoteDetails(id: Int): Note {
        return noteDao.getNoteById(id).mapToNote()
    }

    override suspend fun addNote(note: Note) {
        noteDao.insertNote(note.mapToNoteEntity())
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note.mapToNoteEntity())
    }
}
