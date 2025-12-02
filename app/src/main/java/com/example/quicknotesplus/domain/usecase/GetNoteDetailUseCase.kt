package com.example.quicknotesplus.domain.usecase

import com.example.quicknotesplus.domain.model.Note
import com.example.quicknotesplus.domain.repository.NoteRepository
import javax.inject.Inject

class GetNoteDetailUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(id: Int): Note? {
        return noteRepository.getNoteDetails(id)
    }
}