package com.mgl_uhou.todolist.ui.feature.addedit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mgl_uhou.todolist.data.ToDoRepository
import com.mgl_uhou.todolist.ui.UIEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddEditViewModel(
    private val id: Long? = null,
    private val repository: ToDoRepository
): ViewModel() {
    var title by mutableStateOf("")
        private set

    var description by mutableStateOf<String?>(null)
        private set

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        id?.let {
            viewModelScope.launch {
                repository.getBy(it)?.let { task ->
                    title = task.title
                    description = task.description
                }
            }
        }
    }

    fun onEvent(event: AddEditEvent) = when(event) {
        is AddEditEvent.TitleChanged -> {
            title = event.title
        }
        is AddEditEvent.DescriptionChanged -> {
            description = event.description
        }
        AddEditEvent.Save -> {
            saveToDo()
        }
    }

    private fun saveToDo() {
        viewModelScope.launch {
            if(title.isBlank()) {
                _uiEvent.send(UIEvent.ShowSnackbar("Title cannot be empty"))
                return@launch
            }

            repository.insert(
                id = id,
                title = title,
                description = description,
            )
            _uiEvent.send(UIEvent.NavigateBack)
        }
    }
}