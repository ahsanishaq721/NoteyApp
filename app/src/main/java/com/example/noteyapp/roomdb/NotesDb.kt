package com.example.noteyapp.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NotesDb(context: Context) : RoomDatabase() {

    abstract fun noteDao(): NoteDao // Your abstract DAO accessor

    companion object {
        // Volatile annotation ensures that the value of INSTANCE is always up-to-date
        // and visible to all execution threads.
        @Volatile
        private var INSTANCE: NotesDb? = null

        fun getDatabase(context: Context): NotesDb {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, // Use applicationContext to avoid memory leaks
                    NotesDb::class.java,
                    "notes_db"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}