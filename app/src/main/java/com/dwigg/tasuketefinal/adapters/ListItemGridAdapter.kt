package com.dwigg.tasuketefinal.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dwigg.tasuketefinal.R
import com.dwigg.tasuketefinal.data.entities.ListItem
import com.dwigg.tasuketefinal.utils.inflate
import kotlinx.android.synthetic.main.row_list_item_grid.view.*

class ListItemGridAdapter(private val listener: (ListItem) -> Unit) :
    RecyclerView.Adapter<ListItemGridAdapter.ViewHolder>() {

    private var listItems: List<ListItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.row_list_item_grid))
    }

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItems[position], listener)
    }

    fun updateListItems(listItems: List<ListItem>) {
        this.listItems = listItems
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(listItem: ListItem, listener: (ListItem) -> Unit) = with(itemView) {
            tvListItemTitle.text = listItem.title
            tvListItemTaskCount.text = if (listItem.taskCount == 0) {
                "No Item"
            } else {
                listItem.taskCount.toString() + if (listItem.taskCount > 1) " Items" else " Item"
            }
            setOnClickListener { listener(listItem) }
        }
    }
}