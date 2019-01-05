package com.dwigg.tasuketefinal.ui.fragments

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dwigg.tasuketefinal.R
import com.dwigg.tasuketefinal.ui.activities.MainActivity
import com.dwigg.tasuketefinal.utils.Constants
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        init()
    }

    private fun init() {
        initializeBottomAppBar()
        btnBack.setOnClickListener { goToHomeFragment() }
        tvLinkedin.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun goToHomeFragment() {
        fragmentManager!!.beginTransaction()
            .replace(R.id.container, HomeFragment(), Constants.tagHomeFragment)
            .commit()
    }

    private fun initializeBottomAppBar() {
        (activity as MainActivity).hideFab()
    }
}