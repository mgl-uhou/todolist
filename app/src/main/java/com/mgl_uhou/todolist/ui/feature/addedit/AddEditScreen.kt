package com.mgl_uhou.todolist.ui.feature

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mgl_uhou.todolist.ui.theme.ToDoListTheme

@Composable
fun AddEditScreen() {
   AddEditContent(
       todoId = null,
       navigateBack = {},
       modifier = Modifier
   )
}

@Composable
fun AddEditContent(
    todoId: Long? = null,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = "",
            onValueChange = {},
            placeholder = {
                Text(
                    text = "Title",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = "",
            onValueChange = {},
            placeholder = {
                Text(
                    text = "Description (optional)",
                    style = MaterialTheme.typography.labelLarge,
//                        color = Color.Black
                )
            }
        )
    }
}

@Preview
@Composable
private fun AddEditContentPreview() {
    ToDoListTheme {
        AddEditContent(
            todoId = null,
            navigateBack = {},
            modifier = Modifier
        )
    }
}
