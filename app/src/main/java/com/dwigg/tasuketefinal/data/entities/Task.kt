package com.dwigg.tasuketefinal.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var title: String = "",
    var note: String = "",
    var isDone: Boolean = false,

    @ColumnInfo(name = "created_at")
    var createdAt: Date = Date(),

    @ColumnInfo(name = "modified_at")
    var modifiedAt: Date = Date(),

    var listItemId: Long = 0L
)