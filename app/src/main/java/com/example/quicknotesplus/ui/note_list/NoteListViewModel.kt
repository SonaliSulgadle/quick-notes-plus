package com.example.quicknotesplus.ui.note_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quicknotesplus.domain.model.Note
import com.example.quicknotesplus.domain.usecase.DeleteNoteUseCase
import com.example.quicknotesplus.domain.usecase.GetNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<List<Note>>(emptyList())
    val state = _state.asStateFlow()

    init {
        getNotes()
        println("Viewmodel init called")
    }

    internal fun getNotes() {
        viewModelScope.launch {
            getNotesUseCase()
                .distinctUntilChanged()
                .collect { notes ->
                    _state.value = notes
                }
        }
    }

    internal fun onDeleteClick(note: Note) {
        viewModelScope.launch {
            deleteNoteUseCase(note)
        }
    }
}
