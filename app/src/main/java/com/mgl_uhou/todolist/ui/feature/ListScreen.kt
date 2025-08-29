package com.mgl_uhou.todolist.ui.feature

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mgl_uhou.todolist.domain.Todo
import com.mgl_uhou.todolist.domain.fakeTodo1
import com.mgl_uhou.todolist.domain.fakeTodo2
import com.mgl_uhou.todolist.domain.fakeTodo3
import com.mgl_uhou.todolist.ui.theme.ToDoListTheme

@Composable
fun ListScreen() {

}

@Composable
fun ListContent(todos: List<Todo>) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.consumeWindowInsets(innerPadding)
        ) {
            items(items = todos){
                Text(text = it.title)
            }
        }

    }
}

@Preview
@Composable
private fun ListContentPreview() {
    ToDoListTheme {
        ListContent(todos = listOf(
            fakeTodo1,
            fakeTodo2,
            fakeTodo3
        ))
    }
}