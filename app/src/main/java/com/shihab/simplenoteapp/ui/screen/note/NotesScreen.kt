package com.shihab.simplenoteapp.ui.screen.note

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun NotesScreen(
    onAddNote: () -> Unit,
    viewModel: NotesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddNote) {
                Icon(Icons.Default.Add, contentDescription = "Add note")
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->

        when {
            uiState.isLoading -> CircularProgressIndicator()

            uiState.notes.isEmpty() -> {
                Text(
                    text = "No notes, yet.",
                    modifier = Modifier.fillMaxSize().wrapContentSize()
                )
            }

            else -> {
                LazyColumn(contentPadding = padding) {
                    items(
                        items = uiState.notes,
                        key = { it.id }
                    ) { note ->
                        NoteItem(
                            note = note,
                            onDelete = {
                                viewModel.onEvent(NotesEvent.DeleteNote(note))
                            }
                        )
                    }
                }
            }
        }
    }
}