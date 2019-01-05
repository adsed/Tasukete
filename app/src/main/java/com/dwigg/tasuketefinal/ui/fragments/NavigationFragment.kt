package com.dwigg.tasuketefinal.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dwigg.tasuketefinal.R
import com.dwigg.tasuketefinal.utils.Constants
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_navigation.*

class NavigationFragment : BottomSheetDialogFragment() {

    companion object {
        fun instance() = NavigationFragment()
    }

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_navigation, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initializeNavigationButtonsListener()
    }

    private fun initializeNavigationButtonsListener() {
        btnHome.setOnClickListener { goToHome() }
        btnSettings.setOnClickListener { goToSettings() }
        btnAbout.setOnClickListener { goToAbout() }
    }

    private fun goToHome() =
        goToFragment(HomeFragment(), Constants.tagHomeFragment)

    private fun goToSettings() =
        goToFragment(SettingsFragment(), Constants.tagSettingsFragment)

    private fun goToAbout() =
        goToFragment(AboutFragment(), Constants.tagAboutFragment)

    private fun goToFragment(fragment: Fragment, tag: String) {
        fragmentManager!!.beginTransaction()
            .replace(R.id.container, fragment, tag)
            .commit()
        dismiss()
    }
}