package com.mgl_uhou.todolist.ui.feature.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mgl_uhou.todolist.domain.ToDo
import com.mgl_uhou.todolist.domain.fakeTodo1
import com.mgl_uhou.todolist.domain.fakeTodo2
import com.mgl_uhou.todolist.domain.fakeTodo3
import com.mgl_uhou.todolist.ui.components.ToDoItem
import com.mgl_uhou.todolist.ui.theme.ToDoListTheme

@Composable
fun ListScreen(
    navigateToAddEditScreen: (id: Long?) -> Unit,
    viewModel: ListViewModel
) {
    val todos by viewModel.todos.collectAsState()
    ListContent(
        todos = todos,
        onEvent = viewModel::onEvent,
    )
}

@Composable
fun ListContent(
    todos: List<ToDo>,
    onEvent: (ListEvent) -> Unit,
) {
        LazyColumn(
            modifier = Modifier,
            contentPadding = PaddingValues(16.dp)
        ) {
            itemsIndexed(items = todos){ index, todo ->
                ToDoItem(
                    todo = todo,
                    onCompletedChange = {
                        onEvent(ListEvent.CompleteChanged(todo.id, it))
                    },
                    onItemClick = {
                        onEvent(ListEvent.AddEdit(todo.id))
                    },
                    onDeleteClick = {
                        onEvent(ListEvent.Delete(todo.id))
                    }
                )

                if (index < todos.lastIndex)
                    Spacer(modifier = Modifier.height(16.dp))
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
        ), onEvent = {})
    }
}