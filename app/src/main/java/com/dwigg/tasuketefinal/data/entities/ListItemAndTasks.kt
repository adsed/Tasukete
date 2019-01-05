package com.dwigg.tasuketefinal.data.entities

import androidx.room.Embedded
import androidx.room.Relation

data class ListItemAndTasks(
    @Embedded
    var listItem: ListItem,

    @Relation(
        parentColumn = "id",
        entityColumn = "listItemId",
        entity = Task::class
    )
    var tasks: List<Task>
)