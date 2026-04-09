package com.shihab.simplenoteapp.data.mapper

import com.shihab.simplenoteapp.data.local.NoteEntity
import com.shihab.simplenoteapp.domain.model.Note

fun NoteEntity.toNote(): Note {
    return Note(
        id = id,
        title = title,
        content = content,
        timestamp = timestamp
    )
}

fun Note.toNoteEntity(): NoteEntity {
    return NoteEntity(
        id = id,
        title = title,
        content = content,
        timestamp = timestamp
    )
}