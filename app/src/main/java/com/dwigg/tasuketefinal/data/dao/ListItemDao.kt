package com.dwigg.tasuketefinal.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dwigg.tasuketefinal.data.entities.ListItem

@Dao
interface ListItemDao {

    @Query("SELECT * FROM List ORDER BY id")
    fun getAllListItems(): LiveData<List<ListItem>>

    @Query("SELECT * FROM List WHERE id = :listItemId")
    fun getListItemById(listItemId: Long): LiveData<ListItem>

    @Query("SELECT * FROM List WHERE id = :listItemId")
    fun getNonLiveListItemById(listItemId: Long): ListItem

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertListItem(listItem: ListItem)

    @Update
    fun updateListItem(listItem: ListItem)

    @Query("DELETE FROM List WHERE id = :listItemId")
    fun deleteListItemById(listItemId: Long)

    @Query("UPDATE List SET isSelected = 0")
    fun updateListItemsToUnselected()
}