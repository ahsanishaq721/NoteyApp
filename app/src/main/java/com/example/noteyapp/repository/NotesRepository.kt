package com.example.noteyapp.repository

import androidx.lifecycle.LiveData
import com.example.noteyapp.roomdb.Note
import com.example.noteyapp.roomdb.NoteDao

// mediator between data sources and view model in MVVM
class NotesRepository(private val noteDao: NoteDao) {

    suspend fun insertNote(note: Note) = noteDao.insertNote(note)

    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()
}