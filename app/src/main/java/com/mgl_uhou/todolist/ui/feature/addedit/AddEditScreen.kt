package com.mgl_uhou.todolist.ui.feature.addedit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mgl_uhou.todolist.ui.theme.ToDoListTheme

@Composable
fun AddEditScreen(
    viewModel: AddEditViewModel,
) {
    val title = viewModel.title
    val description = viewModel.description

   AddEditContent(
       title = title,
       description = description,
       onEvent = viewModel::onEvent,
       modifier = Modifier
   )
}

@Composable
fun AddEditContent(
    title: String,
    description: String?,
    onEvent: (AddEditEvent) -> Unit,
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
            value = title,
            onValueChange = {
                onEvent(AddEditEvent.TitleChanged(it))
            },
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
            value = description ?: "",
            onValueChange = {
                onEvent(AddEditEvent.DescriptionChanged(it))
            },
            placeholder = {
                Text(
                    text = "Description (optional)",
                    style = MaterialTheme.typography.labelLarge,
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
            description = null,
            title = "",
            onEvent = {},
            modifier = Modifier
        )
    }
}
