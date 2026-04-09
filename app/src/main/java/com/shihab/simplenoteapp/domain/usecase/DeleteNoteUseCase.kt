package com.shihab.simplenoteapp.domain.usecase

import com.shihab.simplenoteapp.data.repository.NoteRepository
import com.shihab.simplenoteapp.domain.model.Note

class DeleteNoteUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}