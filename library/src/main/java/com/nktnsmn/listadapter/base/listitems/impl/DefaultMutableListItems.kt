package com.nktnsmn.listadapter.base.listitems.impl

import com.nktnsmn.listadapter.base.listitems.MutableListItems
import com.nktnsmn.listadapter.base.listitems.impl.base.BaseUpdatableListItems

open class DefaultMutableListItems<T> : BaseUpdatableListItems<T>(), MutableListItems<T> {

    protected val items: MutableList<T> = mutableListOf()

    override fun getAll(): List<T> = items

    override fun changeFully(newItems: List<T>): Boolean {
        latchNewItems(newItems)
        updateCallback?.onChangedFully()
        return true
    }

    override fun set(newItem: T, position: Int): Boolean =
        if (checkIndexOfBounds(position)) {
            items[position] = newItem
            updateCallback?.onChanged(position, 1, null)
            true
        } else {
            false
        }

    override fun add(newItem: T, position: Int): Boolean =
        if (checkIndexOfBounds(position, size() + 1)) {
            items.add(position, newItem)
            updateCallback?.onInserted(position, 1)
            true
        } else {
            false
        }

    override fun add(newItems: List<T>, position: Int): Boolean =
        if (checkIndexOfBounds(position, size() + 1)) {
            items.addAll(position, newItems)
            updateCallback?.onInserted(position, newItems.size)
            true
        } else {
            false
        }

    override fun remove(position: Int): Boolean =
        if (checkIndexOfBounds(position)) {
            items.removeAt(position)
            updateCallback?.onRemoved(position, 1)
            true
        } else {
            false
        }

    override fun remove(position: Int, count: Int): Boolean {
        var removedCount = 0
        while (checkIndexOfBounds(position) && removedCount <= count) {
            items.removeAt(position)
            removedCount++
        }
        @Suppress("LiftReturnOrAssignment")
        if (removedCount > 0) {
            updateCallback?.onRemoved(position, removedCount)
            return removedCount == count
        } else {
            return false
        }
    }

    override fun removeAll() {
        val countRemoved = items.size
        items.clear()
        updateCallback?.onRemoved(0, countRemoved)
    }

    protected fun latchNewItems(newItems: List<T>) {
        items.clear()
        add(newItems)
    }

    private fun checkIndexOfBounds(position: Int, rightBound: Int = size()): Boolean = position in 0 until rightBound
}