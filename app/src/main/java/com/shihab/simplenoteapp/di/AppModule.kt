package com.shihab.simplenoteapp.di

import android.content.Context
import androidx.room.Room
import com.shihab.simplenoteapp.data.local.NoteDao
import com.shihab.simplenoteapp.data.local.NoteDatabase
import com.shihab.simplenoteapp.data.repository.NoteRepository
import com.shihab.simplenoteapp.data.repository.NoteRepositoryImplement
import com.shihab.simplenoteapp.domain.usecase.AddNoteUseCase
import com.shihab.simplenoteapp.domain.usecase.DeleteNoteUseCase
import com.shihab.simplenoteapp.domain.usecase.GetNotesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NoteDatabase {
        return Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "note_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteDao(db: NoteDatabase): NoteDao = db.noteDao()

    @Provides
    @Singleton
    fun provideRepository(dao: NoteDao): NoteRepository {
        return NoteRepositoryImplement(dao)
    }

    @Provides
    fun provideGetNotesUseCase(repo: NoteRepository) = GetNotesUseCase(repo)

    @Provides
    fun provideAddNoteUseCase(repo: NoteRepository) = AddNoteUseCase(repo)

    @Provides
    fun provideDeleteNoteUseCase(repo: NoteRepository) = DeleteNoteUseCase(repo)
}