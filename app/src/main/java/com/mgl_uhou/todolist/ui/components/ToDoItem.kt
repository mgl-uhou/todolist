package com.mgl_uhou.todolist.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mgl_uhou.todolist.domain.ToDo
import com.mgl_uhou.todolist.domain.fakeTodo1
import com.mgl_uhou.todolist.domain.fakeTodo2
import com.mgl_uhou.todolist.ui.theme.ToDoListTheme
import com.mgl_uhou.todolist.ui.theme.grayIsCompleted

@Composable
fun ToDoItem(
    todo: ToDo,
    onCompletedChange: (Boolean) -> Unit,
    onItemClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = onItemClick,
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 2.dp,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outline
        )
    ){
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = todo.isCompleted,
                onCheckedChange = onCompletedChange
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = todo.title,
                    color = if (todo.isCompleted) grayIsCompleted else Color.Unspecified,
                    style = MaterialTheme.typography.titleLarge.copy(
                        textDecoration = if (todo.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                    )
                )

                todo.description?.let { description ->
                    Spacer(modifier = Modifier.height(8.dp))
                    var newDescription = ""
                    if (todo.isCompleted){
                        newDescription = if(description.length <= 25) description else description.take(25) + "..."
                    }
                    Text(
                        text = if(todo.isCompleted) newDescription else description,
                        color = if (todo.isCompleted) grayIsCompleted else Color.Unspecified,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            textDecoration = if (todo.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = onDeleteClick
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete"
                )
            }
        }
    }
}

@Preview
@Composable
private fun TodoItemPreview() {
    ToDoListTheme {
        ToDoItem(
            todo = fakeTodo1,
            onCompletedChange = {},
            onItemClick = {},
            onDeleteClick = {}
        )
    }
}

@Preview
@Composable
private fun TodoItemCompletedPreview() {
    ToDoListTheme {
        ToDoItem(
            todo = fakeTodo2,
            onCompletedChange = {},
            onItemClick = {},
            onDeleteClick = {}
        )
    }
}
