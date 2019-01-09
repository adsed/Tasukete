package com.dwigg.tasuketefinal.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.dwigg.tasuketefinal.R
import com.dwigg.tasuketefinal.ui.fragments.HomeFragment
import com.dwigg.tasuketefinal.ui.fragments.NavigationFragment
import com.dwigg.tasuketefinal.utils.Constants
import com.google.android.material.bottomappbar.BottomAppBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(bottomAppBar)

        init()
    }

    private fun init() {
        initializeNavigationListener()
        initializeHomeFragment()
        bottomAppBar.overflowIcon = ContextCompat.getDrawable(this, R.drawable.ic_round_more_vert_24dp)
    }

    private fun initializeNavigationListener() {
        bottomAppBar.setNavigationOnClickListener {
            val navigationFragment = NavigationFragment.instance()
            navigationFragment.show(supportFragmentManager, Constants.tagNavigationFragment)
        }
    }

    private fun initializeHomeFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, HomeFragment(), Constants.tagHomeFragment)
            .commit()
    }

    fun setFabAlignmentToEnd() {
        fab.show()
        bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
    }

    fun setFabAlignmentToCenter() {
        fab.show()
        bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
    }

    fun hideFab() {
        fab.hide()
    }
}
