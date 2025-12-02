package com.example.quicknotesplus.domain.usecase

import com.example.quicknotesplus.domain.model.Note
import com.example.quicknotesplus.domain.repository.NoteRepository
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(
    val noteRepository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        noteRepository.addNote(note = note)
    }
}