package com.mgl_uhou.todolist.data

import com.mgl_uhou.todolist.domain.ToDo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ToDoRepositoryImpl(
    private val dao: ToDoDao
): ToDoRepository {
    override suspend fun insert(id: Long?, title: String, description: String?) {
        val entity = id?.let {
            dao.getBy(it)?.copy(
                title = title,
                description = description,
            )
        } ?: ToDoEntity(
            title = title,
            description = description,
            isCompleted = false,
        )
        dao.insert(entity)
    }

    override suspend fun updateCompleted(id: Long, isCompleted: Boolean) {
        val existingEntity = dao.getBy(id) ?: return
        val updatedEntity = existingEntity.copy(isCompleted = isCompleted)
        dao.insert(updatedEntity)
    }

    override suspend fun delete(id: Long) {
        val existingEntity = dao.getBy(id) ?: return
        dao.delete(existingEntity)
    }

    override fun getAll(): Flow<List<ToDo>> {
        return dao.getAll().map { entities ->
           entities.map { entity ->
               toDoReturn(entity)
           }
        }
    }

    override suspend fun getBy(id: Long): ToDo? {
        return dao.getBy(id)?.let { entity ->
            toDoReturn(entity)
        }
    }
}

private fun toDoReturn(entity: ToDoEntity): ToDo{
    return ToDo(
        id = entity.id,
        title = entity.title,
        description = entity.description,
        isCompleted = entity.isCompleted
    )
}