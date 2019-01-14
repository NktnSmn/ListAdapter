package com.nktnsmn.listadapter.items

import android.support.v7.widget.RecyclerView

abstract class AdapterItems<T> {

    protected var adapter: RecyclerView.Adapter<*>? = null

    abstract fun getAll(): List<T>

    open fun attachAdapter(adapter: RecyclerView.Adapter<*>) {
        this.adapter = adapter
    }

    open fun detachAdapter() {
        adapter = null
    }

    fun size(): Int = getAll().size

    @Throws(IndexOutOfBoundsException::class)
    operator fun get(position: Int): T = getAll()[position]

    fun getOrNull(position: Int): T? = getAll().getOrNull(position)
}