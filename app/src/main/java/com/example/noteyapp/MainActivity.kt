package com.example.noteyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.noteyapp.repository.NotesRepository
import com.example.noteyapp.roomdb.NotesDb
import com.example.noteyapp.ui.theme.NoteyAppTheme
import com.example.noteyapp.view.screens.DisplayDialog
import com.example.noteyapp.view.screens.DisplayNotesList
import com.example.noteyapp.viewmodel.NoteViewModel
import com.example.noteyapp.viewmodel.NotesViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val notesDb = NotesDb.getDatabase(applicationContext)
        val noteDao = notesDb.noteDao()
        val repository = NotesRepository(noteDao)
        val viewModelFactory = NotesViewModelFactory(repository)
        val notesViewModel =
            ViewModelProvider(this, viewModelFactory)[NoteViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            NoteyAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        MyFab(notesViewModel)
                    }

                ) {
                    val notes by notesViewModel.allNotes.observeAsState(emptyList())
                    DisplayNotesList(
                        notes = notes,
                        modifier = Modifier.padding(it)
                    )
                }
            }
        }
    }
}

@Composable
fun MyFab(noteViewModel: NoteViewModel) {
    // controlling the dialog appearance
    var showDialog by remember { mutableStateOf(false) }
    DisplayDialog(
        viewModel = noteViewModel,
        showDialog = showDialog,
        onDismiss = { showDialog = false })
    FloatingActionButton(
        onClick = { showDialog = true },
        containerColor = Color.Blue,
        contentColor = Color.White

    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add Note"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NoteyAppTheme {
//        Greeting("Android")
    }
}