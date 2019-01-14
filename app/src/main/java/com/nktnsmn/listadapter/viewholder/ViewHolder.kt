package com.nktnsmn.listadapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View

open class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun onAttachedToWindow() {
    }

    fun onDetachedFromWindow() {
    }

    fun onRecycled() {
    }

    fun onFailedToRecycleView(): Boolean = false
}