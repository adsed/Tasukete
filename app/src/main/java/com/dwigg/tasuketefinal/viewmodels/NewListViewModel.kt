package com.dwigg.tasuketefinal.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.dwigg.tasuketefinal.data.entities.ListItem
import com.dwigg.tasuketefinal.data.repositories.ListItemRepository

class NewListViewModel(application: Application) : AndroidViewModel(application) {

    private val listItemRepository = ListItemRepository(application)

    fun insertListItem(title: String) =
        listItemRepository.insertListItem(ListItem(title = title.toUpperCase()))
}