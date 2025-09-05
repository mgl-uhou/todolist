package com.mgl_uhou.todolist.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.mgl_uhou.todolist.data.ToDoDatabaseProvider
import com.mgl_uhou.todolist.data.ToDoRepositoryImpl
import com.mgl_uhou.todolist.ui.UIEvent
import com.mgl_uhou.todolist.ui.feature.addedit.AddEditScreen
import com.mgl_uhou.todolist.ui.feature.list.ListScreen
import com.mgl_uhou.todolist.ui.feature.addedit.AddEditEvent
import com.mgl_uhou.todolist.ui.feature.addedit.AddEditViewModel
import com.mgl_uhou.todolist.ui.feature.list.ListEvent
import com.mgl_uhou.todolist.ui.feature.list.ListViewModel
import kotlinx.serialization.Serializable

@Serializable
object ListRoute

@Serializable
data class AddEditRoute(val id: Long? = null)

@Composable
fun ToDoNavHost() {
    val navController = rememberNavController()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    var fabOnClick by remember {
        mutableStateOf<() -> Unit>({})
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = fabOnClick
            ) {
                val routeName = currentRoute?.substringBefore("?")
                when(routeName) {
                    ListRoute::class.qualifiedName -> {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Add New Task"
                        )
                    }
                    AddEditRoute::class.qualifiedName -> {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Save Task",
                        )
                    }
                }
            }
        },
        modifier = Modifier.fillMaxSize().systemBarsPadding(),
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ListRoute,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<ListRoute> {
                val context = LocalContext.current.applicationContext
                val database = ToDoDatabaseProvider.provide(context)
                val repository = ToDoRepositoryImpl( dao = database.toDoDao )
                val viewModel = viewModel<ListViewModel> {
                    ListViewModel(repository = repository)
                }

                /* LaunchedEffect(Unit) {
                    viewModel.uiEvent.collect { event ->
                        when(event) {
                            is UIEvent.ShowSnackbar -> {
                                snackbarHostState.showSnackbar(
                                    message = event.message
                                )
                            }
                            UIEvent.NavigateBack -> {
                                navController.popBackStack()
                            }
                            is UIEvent.Navigate<*> -> {

                            }                        }
                    }
                } */

                LaunchedEffect(Unit) {
                    fabOnClick = {
                        viewModel.onEvent(ListEvent.AddEdit(null))
                    }
                }
                ListScreen(
                    navigateToAddEditScreen = { id ->
                        navController.navigate(AddEditRoute(id))
                    },
                    viewModel = viewModel
                )
            }

            composable<AddEditRoute> { backStackEntry ->
                val addEditRoute = backStackEntry.toRoute<AddEditRoute>()

                val context = LocalContext.current.applicationContext
                val database = ToDoDatabaseProvider.provide(context)
                val repository = ToDoRepositoryImpl( dao = database.toDoDao )
                val viewModel = viewModel<AddEditViewModel> {
                    AddEditViewModel(repository = repository)
                }

                LaunchedEffect(Unit) {
                    viewModel.uiEvent.collect { event ->
                        when(event) {
                            is UIEvent.ShowSnackbar -> {
                                snackbarHostState.showSnackbar(
                                    message = event.message
                                )
                            }
                            UIEvent.NavigateBack -> {
                                navController.popBackStack()
                            }
                            is UIEvent.Navigate<*> -> {

                            }                        }
                    }
                }

                LaunchedEffect(viewModel) {
                    fabOnClick = {
                        viewModel.onEvent(AddEditEvent.Save)
                    }
                }
                AddEditScreen(
                    todoId = addEditRoute.id,
                    viewModel = viewModel,
                )
            }
        }
    }

}