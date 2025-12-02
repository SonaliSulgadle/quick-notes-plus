package com.example.quicknotesplus.di

import android.app.Application
import androidx.room.Room
import com.example.quicknotesplus.data.local.AppDatabase
import com.example.quicknotesplus.data.repository.NoteRepositoryImpl
import com.example.quicknotesplus.domain.repository.NoteRepository
import com.example.quicknotesplus.domain.usecase.AddNoteUseCase
import com.example.quicknotesplus.domain.usecase.DeleteNoteUseCase
import com.example.quicknotesplus.domain.usecase.GetNoteDetailUseCase
import com.example.quicknotesplus.domain.usecase.GetNotesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: AppDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao())
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): GetNotesUseCase {
        return GetNotesUseCase(
            noteRepository = repository,
        )
    }

    @Provides
    @Singleton
    fun provideNoteDetailUseCases(repository: NoteRepository): GetNoteDetailUseCase {
        return GetNoteDetailUseCase(
            noteRepository = repository,
        )
    }

    @Provides
    @Singleton
    fun provideAddNoteUseCases(repository: NoteRepository): AddNoteUseCase {
        return AddNoteUseCase(
            noteRepository = repository,
        )
    }

    @Provides
    @Singleton
    fun provideDeleteNoteUseCases(repository: NoteRepository): DeleteNoteUseCase {
        return DeleteNoteUseCase(
            noteRepository = repository,
        )
    }
}