package com.shihab.simplenoteapp.ui.screen.note

import com.shihab.simplenoteapp.domain.model.Note

data class NotesUiState(
    val notes: List<Note> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

sealed class NotesEvent {
    data class DeleteNote(val note: Note) : NotesEvent()
    object UndoDelete : NotesEvent()
}