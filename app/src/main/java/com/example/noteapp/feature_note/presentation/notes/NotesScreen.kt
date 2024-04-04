package com.example.noteapp.feature_note.presentation.notes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.noteapp.R
import com.example.noteapp.feature_note.presentation.notes.components.NoteItem
import com.example.noteapp.feature_note.presentation.notes.components.OrderSection
import com.example.noteapp.feature_note.presentation.util.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    navController: NavController,
    viewModel: NotesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val showMenu = remember { mutableStateOf(false) }

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddEditNoteScreen.route)
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.notes_screen_FAB_icon_description)
                )
            }
        },
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(id = R.string.notes_screen_main_text)) },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            viewModel.onEvent(NotesEvent.ToggleOrderSection)
                        },
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Sort,
                            contentDescription = stringResource(id = R.string.notes_screen_sort_icon_description)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { showMenu.value = true }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = stringResource(id = R.string.notes_screen_more_icon_description)
                        )
                    }
                    DropdownMenu(
                        expanded = showMenu.value,
                        onDismissRequest = { showMenu.value = false },
                        offset = DpOffset((-16).dp, 0.dp)
                    ) {
                        DropdownMenuItem(
                            text = { Text(text = stringResource(id = R.string.notes_screen_menu_log_out)) },
                            onClick = { /*TODO*/ })
                    }
                }

            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            AnimatedVisibility(
                visible = state.isOrderSectionVisible
            ) {
                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth(),
                    noteOrder = state.noteOrder,
                    onOrderChange = { noteOrder ->
                        viewModel.onEvent(NotesEvent.Order(noteOrder))
                    }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.notes, key = { it.id ?: it.timestamp }) { note ->
                    NoteItem(
                        note = note,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    Screen.AddEditNoteScreen.route +
                                            "?noteId=${note.id}&noteColor=${note.color}"
                                )
                            }
                            .animateItemPlacement(),
                        onDeleteClick = {
                            viewModel.onEvent(NotesEvent.DeleteNote(note))
                            scope.launch {
                                val result = snackBarHostState.showSnackbar(
                                    message = context.getString(R.string.notes_screen_onDelete_snackBar_message),
                                    actionLabel = context.getString(R.string.notes_screen_onDelete_snackBar_actionLabel),
                                    duration = SnackbarDuration.Long,
                                    withDismissAction = true
                                )
                                if (result == SnackbarResult.ActionPerformed) {
                                    viewModel.onEvent(NotesEvent.RestoreNote)
                                }
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}