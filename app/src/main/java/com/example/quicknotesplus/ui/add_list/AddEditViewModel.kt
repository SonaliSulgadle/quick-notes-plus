package com.example.quicknotesplus.ui.add_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quicknotesplus.domain.model.Note
import com.example.quicknotesplus.domain.usecase.AddNoteUseCase
import com.example.quicknotesplus.domain.usecase.GetNoteDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val getNoteDetailUseCase: GetNoteDetailUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val noteId = savedStateHandle.get<Int>(NOTE_ID) ?: NOTES_EMPTY
    private val _state = mutableStateOf(AddEditNoteUiState())
    internal val state = _state

    init {
        if (noteId != -1) {
            viewModelScope.launch {
                getNoteDetailUseCase(noteId)?.let {
                    _state.value = state.value.copy(
                        title = it.title,
                        content = it.content,
                    )
                }
            }
        }
    }

    internal fun saveNote() {
        viewModelScope.launch {
            addNoteUseCase(
                Note(
                    id = if (noteId != NOTES_EMPTY) noteId else NOTE_DEFAULT,
                    title = state.value.title,
                    content = state.value.content,
                    timestamp = System.currentTimeMillis()
                )
            )
            _state.value = state.value.copy(isSaved = true)
        }
    }

    internal fun onTitleChange(newTitle: String) {
        _state.value = state.value.copy(title = newTitle)
    }

    internal fun onContentChange(newContent: String) {
        _state.value = state.value.copy(content = newContent)
    }

    companion object {
        internal const val NOTES_EMPTY = -1
        private const val NOTE_DEFAULT = 0
        private const val NOTE_ID = "id"
    }
}