package com.dwigg.tasuketefinal.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dwigg.tasuketefinal.R
import com.dwigg.tasuketefinal.viewmodels.NewListViewModel
import com.dwigg.tasuketefinal.viewmodels.NewTaskViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_new_list.*

class NewListFragment : BottomSheetDialogFragment() {

    private lateinit var viewModel: NewListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_new_list, container, false)
    }

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NewListViewModel::class.java)

        initializeButtonListeners()
    }

    private fun initializeButtonListeners() {
        btnClose.setOnClickListener { dismiss() }
        btnSave.setOnClickListener { saveList() }
        etListTitle.setOnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_DONE) {
                saveList()
                true
            } else false
        }
    }

    private fun saveList() {
        if (etListTitle.text.trim().isEmpty()) return
        viewModel.insertListItem(etListTitle.text.trim().toString())
        dismiss()
    }
}