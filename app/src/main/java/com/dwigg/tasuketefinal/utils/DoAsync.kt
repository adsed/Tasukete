package com.dwigg.tasuketefinal.utils

import android.os.AsyncTask

class DoAsync(private val handler: () -> Unit) : AsyncTask<Void, Void, Void>() {

    override fun doInBackground(vararg params: Void?): Void? {
        handler()
        return null
    }
}