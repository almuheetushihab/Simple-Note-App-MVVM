package com.shihab.simplenoteapp.domain.usecase

import com.shihab.simplenoteapp.data.repository.NoteRepository
import com.shihab.simplenoteapp.domain.model.Note

class GetNoteUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}