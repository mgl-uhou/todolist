package com.mgl_uhou.todolist.ui

sealed interface UIEvent {
    data class ShowSnackbar(val message: String): UIEvent
    data object NavigateBack: UIEvent
    data class Navigate<T: Any>(val route: T): UIEvent
}