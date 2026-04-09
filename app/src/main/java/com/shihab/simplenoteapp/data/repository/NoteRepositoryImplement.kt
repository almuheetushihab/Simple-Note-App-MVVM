package com.shihab.simplenoteapp.data.repository

import com.shihab.simplenoteapp.data.local.NoteDao
import com.shihab.simplenoteapp.data.mapper.toNote
import com.shihab.simplenoteapp.data.mapper.toNoteEntity
import com.shihab.simplenoteapp.domain.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class NoteRepositoryImplement(
    private val dao: NoteDao
) : NoteRepository {

    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes().map { entities ->
            entities.map { it.toNote() }
        }
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note.toNoteEntity())
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note.toNoteEntity())
    }
}