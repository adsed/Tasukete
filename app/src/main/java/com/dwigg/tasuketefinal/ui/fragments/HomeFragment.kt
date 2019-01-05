package com.dwigg.tasuketefinal.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.dwigg.tasuketefinal.R
import com.dwigg.tasuketefinal.adapters.ListItemGridAdapter
import com.dwigg.tasuketefinal.data.entities.ListItem
import com.dwigg.tasuketefinal.data.entities.ListItemAndTasks
import com.dwigg.tasuketefinal.ui.activities.MainActivity
import com.dwigg.tasuketefinal.utils.Constants
import com.dwigg.tasuketefinal.viewmodels.HomeViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var listItemGridAdapter: ListItemGridAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        init()
    }

    private fun init() {
        initializeBottomAppBar()
        initializeListItemListAdapter()
        initializeListItemList()
    }


    private fun initializeListItemListAdapter() {
        listItemGridAdapter = ListItemGridAdapter {
            onListItemClick(it)
        }

        viewModel.getAllListItemAndTasks().observe(this, Observer {
            val listItems = ArrayList<ListItem>()
            for (listItemAndTasks: ListItemAndTasks in it!!) {
                val listItem = listItemAndTasks.listItem
                listItem.taskCount = listItemAndTasks.tasks.size
                listItems.add(listItem)
            }

            if (listItems.isEmpty()) {
                tvEmpty.visibility = View.VISIBLE
                rvListItemList.visibility = View.GONE
            } else {
                tvEmpty.visibility = View.GONE
                rvListItemList.visibility = View.VISIBLE
                listItemGridAdapter.updateListItems(listItems)
            }
        })
    }

    private fun initializeListItemList() {
        rvListItemList.layoutManager = GridLayoutManager(context, 2)
        rvListItemList.itemAnimator = DefaultItemAnimator()
        rvListItemList.adapter = listItemGridAdapter
    }

    private fun initializeBottomAppBar() {
        val mainActivity = (activity as MainActivity)
        mainActivity.setFabAlignmentToEnd()
        mainActivity.fab.setOnClickListener {
            val newListFragment = NewListFragment()
            newListFragment.show(fragmentManager!!, Constants.tagNewListFragment)
        }
        mainActivity.bottomAppBar.menu.clear()
    }

    private fun onListItemClick(listItem: ListItem) {
        val taskListFragment = TaskListFragment.newInstance(listItem.id)
        fragmentManager!!.beginTransaction()
            .replace(R.id.container, taskListFragment, Constants.tagTaskListFragment)
            .addToBackStack(null)
            .commit()
    }
}