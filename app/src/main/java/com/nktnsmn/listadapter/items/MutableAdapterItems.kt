package com.nktnsmn.listadapter.items

import com.nktnsmn.listadapter.checkIndexOfBounds

open class MutableAdapterItems<T> : AdapterItems<T>() {

    private val items: MutableList<T> = mutableListOf()

    override fun getAll(): List<T> = items

    fun set(items: List<T>) {
        this.items.clear()
        add(items)
        adapter?.notifyDataSetChanged()
    }

    fun add(item: T) {
        items.add(item)
        adapter?.notifyItemInserted(items.lastIndex)
    }

    fun add(items: List<T>) {
        val fromIndex = this.items.size
        this.items.addAll(items)
        adapter?.notifyItemRangeInserted(fromIndex, items.size)
    }

    fun set(item: T, position: Int): Boolean =
        if (items.checkIndexOfBounds(position)) {
            items[position] = item
            adapter?.notifyItemChanged(position)
            true
        } else {
            false
        }

    fun remove(position: Int): Boolean =
        if (items.checkIndexOfBounds(position)) {
            items.removeAt(position)
            adapter?.notifyItemRemoved(position)
            true
        } else {
            false
        }
}