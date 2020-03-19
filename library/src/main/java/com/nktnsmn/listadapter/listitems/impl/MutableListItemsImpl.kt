package com.nktnsmn.listadapter.listitems.impl

import com.nktnsmn.listadapter.listitems.MutableListItems
import com.nktnsmn.listadapter.observer.ListItemsObserver
import com.nktnsmn.listadapter.util.UiThreadVar
import com.nktnsmn.listadapter.util.checkUiThread

class MutableListItemsImpl<ITEM : Any> : MutableListItems<ITEM> {

    override var observer: ListItemsObserver? by UiThreadVar()
    private var items: ArrayList<ITEM> = ArrayList()

    override fun getAll(): List<ITEM> = items

    override fun changeFully(newItems: List<ITEM>): Boolean {
        checkUiThread()
        items = ArrayList(newItems)
        observer?.onChangedFully()
        return true
    }

    override fun set(newItem: ITEM, position: Int): Boolean {
        checkUiThread()
        return if (checkIndexOfBounds(position)) {
            items[position] = newItem
            observer?.onChanged(position, 1, null)
            true
        } else {
            false
        }
    }

    override fun add(newItems: List<ITEM>, position: Int): Boolean {
        checkUiThread()
        return if (checkIndexOfBounds(position, size())) {
            items.addAll(position, newItems)
            observer?.onInserted(position, newItems.size)
            true
        } else {
            false
        }
    }

    override fun remove(position: Int, count: Int): Boolean {
        checkUiThread()
        return if (checkIndexOfBounds(position)) {
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
    }

    override fun removeAll() {
        checkUiThread()
        val removedCount = items.size
        items.clear()
        observer?.onRemoved(0, removedCount)
    }

    private fun checkIndexOfBounds(position: Int, rightBound: Int = getAll().lastIndex): Boolean =
        position in 0..rightBound
}