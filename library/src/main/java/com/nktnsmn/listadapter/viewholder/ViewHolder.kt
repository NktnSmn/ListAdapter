package com.nktnsmn.listadapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class ViewHolder<DATA : Any>(view: View) : RecyclerView.ViewHolder(view) {

    fun onAttachedToWindow() {
    }

    fun onDetachedFromWindow() {
    }

    fun onRecycled() {
    }

    fun onFailedToRecycleView(): Boolean = false

    @Suppress("UNCHECKED_CAST")
    fun bindData(data: Any) {
        bindDataInternal(data as DATA)
    }

    @Suppress("UNCHECKED_CAST")
    fun bindData(data: Any, payloads: MutableList<Any>) {
        bindDataInternal(data as DATA, payloads)
    }

    protected abstract fun bindDataInternal(data: DATA)

    protected open fun bindDataInternal(data: DATA, payloads: MutableList<Any>) {
        bindDataInternal(data)
    }
}