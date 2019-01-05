package com.dwigg.tasuketefinal.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dwigg.tasuketefinal.R
import com.dwigg.tasuketefinal.data.entities.ListItem
import com.dwigg.tasuketefinal.utils.inflate
import kotlinx.android.synthetic.main.row_list_item.view.*

class ListItemListAdapter(
    private val listener: (ListItem) -> Unit
) : RecyclerView.Adapter<ListItemListAdapter.ViewHolder>() {

    private var listItemList: List<ListItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.row_list_item))
    }

    override fun getItemCount() = listItemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItemList[position], listener)
    }

    fun updateListItemList(listItemList: List<ListItem>) {
        this.listItemList = listItemList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(listItem: ListItem, listener: (ListItem) -> Unit) = with(itemView) {
            tvListItemTitle.text = listItem.title
            imgCheckIcon.visibility = if (listItem.isSelected) View.VISIBLE else View.GONE
            setOnClickListener { listener(listItem) }
        }
    }
}