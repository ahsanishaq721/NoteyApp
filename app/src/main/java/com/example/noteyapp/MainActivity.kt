package com.example.noteyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.noteyapp.repository.NotesRepository
import com.example.noteyapp.roomdb.Note
import com.example.noteyapp.roomdb.NotesDb
import com.example.noteyapp.ui.theme.NoteyAppTheme
import com.example.noteyapp.viewmodel.NoteViewModel
import com.example.noteyapp.viewmodel.NotesViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteyAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val notesDb = NotesDb.getDatabase(applicationContext)
                    val noteDao = notesDb.noteDao()
                    val repository = NotesRepository(noteDao)
                    val viewModelFactory = NotesViewModelFactory(repository)
                    val notesViewModel =
                        ViewModelProvider(this, viewModelFactory)[NoteViewModel::class.java]
                    val notes = notesViewModel.allNotes.observeAsState(emptyList())

                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NoteyAppTheme {
        Greeting("Android")
    }
}