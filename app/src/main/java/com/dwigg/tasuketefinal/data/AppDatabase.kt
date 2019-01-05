package com.dwigg.tasuketefinal.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dwigg.tasuketefinal.data.dao.ListItemAndTasksDao
import com.dwigg.tasuketefinal.data.dao.ListItemDao
import com.dwigg.tasuketefinal.data.dao.TaskDao
import com.dwigg.tasuketefinal.data.entities.ListItem
import com.dwigg.tasuketefinal.data.entities.Task
import com.dwigg.tasuketefinal.utils.Converters

@Database(
    entities = [
        ListItem::class,
        Task::class
    ], version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun listItemDao(): ListItemDao
    abstract fun taskDao(): TaskDao
    abstract fun listItemAndTaskDao(): ListItemAndTasksDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "tasukere.db"
                    ).build()
                }
            }
            return instance
        }

        fun getTestInstance(context: Context): AppDatabase? {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.inMemoryDatabaseBuilder(
                        context,
                        AppDatabase::class.java
                    ).build()
                }
            }
            return instance
        }
    }
}