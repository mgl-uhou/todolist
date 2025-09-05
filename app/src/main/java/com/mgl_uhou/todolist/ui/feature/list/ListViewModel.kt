package com.mgl_uhou.todolist.ui.feature.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mgl_uhou.todolist.data.ToDoRepository
import com.mgl_uhou.todolist.navigation.AddEditRoute
import com.mgl_uhou.todolist.ui.UIEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ListViewModel(
    private val repository: ToDoRepository
): ViewModel() {
    val todos = repository.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: ListEvent) = when(event) {
        is ListEvent.Delete -> {
            delete(event.id)
        }
        is ListEvent.CompleteChanged -> {
            completeChanged(event.id, event.isCompleted)
        }
        is ListEvent.AddEdit -> {
            viewModelScope.launch {
                _uiEvent.send(UIEvent.Navigate(AddEditRoute(event.id)))
            }
        }
    }

    private fun delete(id: Long){
        viewModelScope.launch {
            repository.delete(id)
        }
    }

    private fun completeChanged(id: Long, isCompleted: Boolean){
        viewModelScope.launch {
            repository.updateCompleted(id, isCompleted)
        }
    }
}