package com.mgl_uhou.todolist.domain

data class Todo(
    val id: Long,
    val title: String,
    val description: String?,
    val isCompleted: Boolean
)

// Fake Object
val fakeTodo1 = Todo(
    id = 1,
    title = "Title 1",
    description = "Description 1",
    isCompleted = false
)

val fakeTodo2: Todo = Todo(
    id = 2,
    title = "Title 2",
    description = "Description 2",
    isCompleted = true
)

val fakeTodo3 = Todo(
    id = 3,
    title = "Title 3",
    description = "Description 3",
    isCompleted = false
)
