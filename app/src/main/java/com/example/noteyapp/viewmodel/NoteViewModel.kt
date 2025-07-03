package com.example.noteyapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteyapp.repository.NotesRepository
import com.example.noteyapp.roomdb.Note
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NotesRepository) : ViewModel() {

    val allNotes = repository.allNotes

    fun insert(note: Note) =
        viewModelScope.launch { // coroutine is tied to the view model life cycle ensuring it is cancelled when the view model is destroyed
            repository.insertNote(note)
        }
}