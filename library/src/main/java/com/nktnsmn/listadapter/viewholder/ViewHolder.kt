package com.nktnsmn.listadapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ViewHolder<out DATA : Any>(view: View) : RecyclerView.ViewHolder(view) {

    fun onAttachedToWindow() = Unit

    fun onDetachedFromWindow() = Unit

    fun onRecycled() = Unit

    fun onFailedToRecycleView(): Boolean = false

    abstract fun bindData(data: @UnsafeVariance DATA)

    open fun bindData(data: @UnsafeVariance DATA, payloads: List<Any>) {
        bindData(data)
    }
}