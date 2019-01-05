package com.dwigg.tasuketefinal.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.dwigg.tasuketefinal.data.entities.ListItem
import com.dwigg.tasuketefinal.data.repositories.ListItemRepository

class TransferTaskViewModel(application: Application) : AndroidViewModel(application) {

    private val listItemRepository = ListItemRepository(application)
    lateinit var previousListItem: ListItem
    lateinit var currentListItem: ListItem

    fun getListItems() = listItemRepository.getAllListItems()

    fun getListItemById(listItemId: Long) = listItemRepository.getListItemById(listItemId)

    fun getNonLiveListItemById(listItemId: Long, callback: (ListItem) -> Unit)
            = listItemRepository.getNonLiveListItemById(listItemId, callback)

    fun updateListItem(listItem: ListItem) = listItemRepository.updateListItem(listItem)

    fun updateListItemsToUnselected() = listItemRepository.updateListItemsToUnselected()
}
