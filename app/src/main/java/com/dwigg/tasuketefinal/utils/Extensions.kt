package com.dwigg.tasuketefinal.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachedToParent: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachedToParent)
}

fun ImageView.setColor(@ColorRes colorRes: Int) {
    setColorFilter((ContextCompat.getColor(context, colorRes)))
}