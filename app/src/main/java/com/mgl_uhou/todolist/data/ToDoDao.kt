package com.mgl_uhou.todolist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ToDoEntity)

    @Delete
    suspend fun delete(entity: ToDoEntity)

    @Query("SELECT * FROM todos")
    fun getAll(): Flow<List<ToDoEntity>>

    @Query("SELECT * FROM todos WHERE id = :id")
    suspend fun getBy(id: Long): ToDoEntity?
}