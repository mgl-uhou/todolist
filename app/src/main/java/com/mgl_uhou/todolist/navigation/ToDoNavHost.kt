package com.mgl_uhou.todolist.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.mgl_uhou.todolist.ui.feature.AddEditScreen
import com.mgl_uhou.todolist.ui.feature.ListScreen
import kotlinx.serialization.Serializable

@Serializable
object ListRoute

@Serializable
data class AddEditRoute(val id: Long? = null)

@Composable
fun ToDoNavHost() {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    var fabOnClick by remember {
        mutableStateOf<() -> Unit>({})
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        floatingActionButton = {
            if(currentRoute == ListRoute::class.qualifiedName) {
                FloatingActionButton(
                    onClick = fabOnClick
                ) {
                    when(currentRoute) {
                        ListRoute::class.qualifiedName -> {
                            Icon(Icons.Default.Add, contentDescription = "Add New Task")
                        }
                        AddEditRoute::class.qualifiedName -> {
                            Icon(Icons.Default.Check, contentDescription = "Save Task")
                        }
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
                LaunchedEffect(Unit) {
                    fabOnClick = {
                        navController.navigate(AddEditRoute(id = null))
                    }
                }
                ListScreen(
                    navigateToAddEditScreen = { id ->
                        navController.navigate(AddEditRoute(id))
                    }
                )
            }

            composable<AddEditRoute> { backStackEntry ->
                val addEditRoute = backStackEntry.toRoute<AddEditRoute>()

                // TODO: Chamar o método de salvamento
                //LaunchedEffect(/*Método de salvamento*/) { fabOnClick({/*método.saveToDo()*/, navController.popBackStack()}) }}) }
                AddEditScreen()
            }
        }
    }

}