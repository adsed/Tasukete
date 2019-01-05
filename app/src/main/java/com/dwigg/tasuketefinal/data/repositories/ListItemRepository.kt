package com.dwigg.tasuketefinal.data.repositories

import android.content.Context
import com.dwigg.tasuketefinal.data.AppDatabase
import com.dwigg.tasuketefinal.data.entities.ListItem
import com.dwigg.tasuketefinal.utils.DoAsync

class ListItemRepository(context: Context) {

    private val appDatabase = AppDatabase.getTestInstance(context)

    fun getAllListItems() = appDatabase!!.listItemDao().getAllListItems()

    fun getListItemById(listItemId: Long) = appDatabase!!.listItemDao().getListItemById(listItemId)

    fun getNonLiveListItemById(listItemId: Long, callback: (ListItem) -> Unit) {
        DoAsync {
            callback(appDatabase!!.listItemDao().getNonLiveListItemById(listItemId))
        }.execute()
    }

    fun insertListItem(listItem: ListItem) {
        DoAsync {
            appDatabase!!.listItemDao().insertListItem(listItem)
        }.execute()
    }

    fun updateListItem(listItem: ListItem) {
        DoAsync {
            appDatabase!!.listItemDao().updateListItem(listItem)
        }.execute()
    }

    fun updateListItemsToUnselected() {
        DoAsync {
            appDatabase!!.listItemDao().updateListItemsToUnselected()
        }.execute()
    }

    fun deleteListItemById(listItemId: Long) {
        DoAsync {
            appDatabase!!.listItemDao().deleteListItemById(listItemId)
        }.execute()
    }
}