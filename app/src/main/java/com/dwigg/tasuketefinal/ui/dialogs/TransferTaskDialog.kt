package com.dwigg.tasuketefinal.ui.dialogs

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.RelativeLayout
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.dwigg.tasuketefinal.R
import com.dwigg.tasuketefinal.adapters.ListItemListAdapter
import com.dwigg.tasuketefinal.data.entities.ListItem
import com.dwigg.tasuketefinal.utils.Constants
import com.dwigg.tasuketefinal.viewmodels.TransferTaskViewModel
import kotlinx.android.synthetic.main.dialog_transfer_task.*

class TransferTaskDialog : DialogFragment() {

    private lateinit var viewModel: TransferTaskViewModel
    private lateinit var listItemListAdapter: ListItemListAdapter
    private var listItemId = 0L
    private lateinit var listener: (ListItem, DialogFragment) -> Unit

    companion object {
        fun newInstance(listItemId: Long, listener: (ListItem, DialogFragment) -> Unit) = TransferTaskDialog().apply {
            this.listener = listener
            arguments = Bundle().apply {
                putLong(Constants.listItemIdKey, listItemId)
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val root = RelativeLayout(activity)
        root.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT)
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(root)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_transfer_task, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TransferTaskViewModel::class.java)

        init()
    }

    private fun init() {
        initializeSelectedListItem()
        initializeListItemListAdapter()
        initializeListItemList()
        initializeButtonListeners()
    }

    private fun initializeSelectedListItem() {
        listItemId = arguments!!.getLong(Constants.listItemIdKey)
        viewModel.updateListItemsToUnselected()
        viewModel.getNonLiveListItemById(listItemId) {
            viewModel.currentListItem = it
            viewModel.currentListItem.isSelected = true
            viewModel.updateListItem(viewModel.currentListItem)
            viewModel.previousListItem = viewModel.currentListItem
        }
    }

    private fun initializeListItemListAdapter() {
        listItemListAdapter = ListItemListAdapter {
            it.isSelected = true
            viewModel.currentListItem = it
            viewModel.updateListItem(viewModel.currentListItem)

            viewModel.previousListItem.isSelected = false
            viewModel.updateListItem(viewModel.previousListItem)
            viewModel.previousListItem = viewModel.currentListItem
        }

        viewModel.getListItems().observe(this, Observer {
            listItemListAdapter.updateListItemList(it!!)
        })
    }

    private fun initializeListItemList() {
        rvListItemList.layoutManager = LinearLayoutManager(context)
        rvListItemList.itemAnimator = DefaultItemAnimator()
        rvListItemList.adapter = listItemListAdapter
    }

    private fun initializeButtonListeners() {
        btnCancel.setOnClickListener { dismiss() }
        btnConfirm.setOnClickListener { listener(viewModel.currentListItem, this) }
    }
}