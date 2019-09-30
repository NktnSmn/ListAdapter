package com.nktnsmn.listadapter.listitems.impl

import android.support.annotation.UiThread
import com.nktnsmn.listadapter.listitems.MutableListItems

open class DefaultMutableListItems<ITEM : Any> : BaseObservableListItems<ITEM>(), MutableListItems<ITEM> {

    private var items: ArrayList<ITEM> = ArrayList()

    override fun getAll(): List<ITEM> = items

    override fun changeFully(newItems: List<ITEM>): Boolean {
        latchItems(newItems)
        observer?.onChangedFully()
        return true
    }

    override fun set(newItem: ITEM, position: Int): Boolean =
        if (checkIndexOfBounds(position)) {
            items[position] = newItem
            observer?.onChanged(position, 1, null)
            true
        } else {
            false
        }

    override fun add(newItem: ITEM, position: Int): Boolean =
        if (checkIndexOfBounds(position, size())) {
            items.add(position, newItem)
            observer?.onInserted(position, 1)
            true
        } else {
            false
        }

    override fun add(newItems: List<ITEM>, position: Int): Boolean =
        if (checkIndexOfBounds(position, size())) {
            items.addAll(position, newItems)
            observer?.onInserted(position, newItems.size)
            true
        } else {
            false
        }

    override fun remove(position: Int, count: Int): Boolean =
        if (checkIndexOfBounds(position)) {
            val listIterator = items.listIterator(position)
            var removedCount = 0
            while (listIterator.hasNext() && removedCount <= count) {
                listIterator.next()
                listIterator.remove()
                removedCount++
            }
            if (removedCount > 0) {
                observer?.onRemoved(position, removedCount)
                removedCount == count
            } else {
                count == 0
            }
        } else {
            false
        }

    override fun removeAll() {
        val removedCount = items.size
        items.clear()
        observer?.onRemoved(0, removedCount)
    }

    @UiThread
    protected fun latchItems(newItems: List<ITEM>) {
        items = ArrayList(newItems)
    }

    private fun checkIndexOfBounds(position: Int, rightBound: Int = getAll().lastIndex): Boolean =
        position in 0..rightBound
}