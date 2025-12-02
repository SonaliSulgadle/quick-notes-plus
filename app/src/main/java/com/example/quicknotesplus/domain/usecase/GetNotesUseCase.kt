package com.example.quicknotesplus.domain.usecase

import com.example.quicknotesplus.domain.model.Note
import com.example.quicknotesplus.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    operator fun invoke(): Flow<List<Note>> {
        return noteRepository.getNotes()
    }
}