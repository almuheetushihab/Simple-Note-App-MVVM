package com.shihab.simplenoteapp.domain.usecase

import com.shihab.simplenoteapp.data.repository.NoteRepository
import com.shihab.simplenoteapp.domain.model.Note

class AddNoteUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("The title of the note can't be empty.")
        }
        repository.insertNote(note)
    }
}

class InvalidNoteException(message: String) : Exception(message)