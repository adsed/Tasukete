package com.dwigg.tasuketefinal.data.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "List")
data class ListItem(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var title: String = "",
    var isSelected: Boolean = false,

    @Ignore
    var taskCount: Int = 0
)