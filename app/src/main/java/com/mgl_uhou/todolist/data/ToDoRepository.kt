package com.mgl_uhou.todolist.data

import com.mgl_uhou.todolist.domain.ToDo
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {
    suspend fun insert(title: String, description: String?)
    suspend fun updateCompleted(id: Long, isCompleted: Boolean)
    suspend fun delete(id: Long)
    fun getAll(): Flow<List<ToDo>>
    suspend fun getBy(id: Long): ToDo?
}