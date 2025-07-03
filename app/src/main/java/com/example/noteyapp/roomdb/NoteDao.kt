package com.example.noteyapp.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
@Dao
interface NoteDao {
    @Insert
    suspend fun insertNote(note: Note)
    @Query("SELECT * FROM notes_table")
    fun getAllNotes(): LiveData<List<Note>> // represents a observable list of notes that can be observed within the lifecycle aware components
}