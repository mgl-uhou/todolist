package com.mgl_uhou.todolist.domain

data class ToDo(
    val id: Long,
    val title: String,
    val description: String?,
    val isCompleted: Boolean
)

// Fake Object
val fakeTodo1 = ToDo(
    id = 1,
    title = "Title 1",
    description = "Description 1",
    isCompleted = false
)

val fakeTodo2: ToDo = ToDo(
    id = 2,
    title = "Title 2",
    description = "Description 2",
    isCompleted = true
)

val fakeTodo3 = ToDo(
    id = 3,
    title = "Title 3",
    description = "Description 3",
    isCompleted = false
)
