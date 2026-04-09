package com.shihab.simplenoteapp.domain.usecase

import com.shihab.simplenoteapp.data.repository.NoteRepository
import com.shihab.simplenoteapp.domain.model.Note
import kotlinx.coroutines.flow.Flow

class GetNotesUseCase(
    private val repository: NoteRepository
) {
    operator fun invoke() : Flow<List<Note>> {
        return repository.getNotes()
    }
}