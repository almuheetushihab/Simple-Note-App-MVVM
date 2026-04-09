package com.shihab.simplenoteapp.ui.screen.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shihab.simplenoteapp.domain.model.Note
import com.shihab.simplenoteapp.domain.usecase.AddNoteUseCase
import com.shihab.simplenoteapp.domain.usecase.DeleteNoteUseCase
import com.shihab.simplenoteapp.domain.usecase.GetNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getNotes: GetNotesUseCase,
    private val deleteNote: DeleteNoteUseCase,
    private val addNote: AddNoteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(NotesUiState())
    val uiState: StateFlow<NotesUiState> = _uiState.asStateFlow()

    private var recentlyDeletedNote: Note? = null

    init {
        loadNotes()
    }

    private fun loadNotes() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            getNotes()
                .catch { e ->
                    _uiState.update {
                        it.copy(isLoading = false, errorMessage = e.message)
                    }
                }
                .collect { notes ->
                    _uiState.update {
                        it.copy(notes = notes, isLoading = false)
                    }
                }
        }
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    deleteNote(event.note)
                    recentlyDeletedNote = event.note
                }
            }

            is NotesEvent.UndoDelete -> {
                viewModelScope.launch {
                    recentlyDeletedNote?.let { note ->
                        addNote(note)
                    }
                    recentlyDeletedNote = null
                }
            }
        }
    }
}