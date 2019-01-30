package com.nktnsmn.listadapter.base.listitems.impl

import android.support.v7.util.DiffUtil
import com.nktnsmn.listadapter.base.diff.DiffCallback
import com.nktnsmn.listadapter.base.listitems.ConvertibleListItems

open class DefaultConvertibleListItems<T>(
    private val diffItemCallback: DiffUtil.ItemCallback<T>
) : DefaultMutableListItems<T>(), ConvertibleListItems<T> {

    override fun convertTo(newItems: List<T>) {
        when {
            newItems.isEmpty() -> removeAll()
            isEmpty() -> add(newItems)
            updateCallback == null -> latchNewItems(newItems)
            else -> {
                val diffResult = DiffUtil.calculateDiff(DiffCallback(getAll(), newItems, diffItemCallback))
                latchNewItems(newItems)
                diffResult.dispatchUpdatesTo(updateCallback)
            }
        }
    }
}