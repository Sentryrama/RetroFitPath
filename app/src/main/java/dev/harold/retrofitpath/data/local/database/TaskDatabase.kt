package dev.harold.retrofitpath.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.harold.retrofitpath.data.local.dao.TaskDao
import dev.harold.retrofitpath.data.local.entity.TaskEntity

@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
abstract class TaskDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}