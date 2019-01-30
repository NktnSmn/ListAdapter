package com.nktnsmn.listadapter.base.listitems.impl

import android.support.v7.util.DiffUtil
import com.nktnsmn.listadapter.base.diff.async.AsyncListDiffer
import com.nktnsmn.listadapter.base.listitems.ConvertibleListItems
import com.nktnsmn.listadapter.base.listitems.impl.base.BaseUpdatableListItems
import java.util.*

class AsyncConvertibleListItems<T>(
    diffItemCallback: DiffUtil.ItemCallback<T>
) : BaseUpdatableListItems<T>(),
    ConvertibleListItems<T>,
    AsyncListDiffer.OnDiffCompletedCallback<T> {

    private var asyncListDiffer: AsyncListDiffer<T> =
        AsyncListDiffer(diffItemCallback, this)
    private var items: List<T> = emptyList()
    private var readOnlyItems: List<T> = Collections.unmodifiableList(emptyList())

    override fun getAll(): List<T> = readOnlyItems

    override fun convertTo(newItems: List<T>) {
        when {
            newItems.isEmpty() -> {
                val countRemoved = size()
                latchNewItems(emptyList())
                updateCallback?.onRemoved(0, countRemoved)
            }
            items.isEmpty() -> {
                latchNewItems(newItems)
                updateCallback?.onInserted(0, newItems.size)
            }
            updateCallback == null -> latchNewItems(newItems)
            else -> asyncListDiffer.calculateDiffAsync(items, newItems)
        }
    }

    fun release() {
        asyncListDiffer.release()
    }

    override fun onDiffCompleted(newList: List<T>, diffResult: DiffUtil.DiffResult) {
        latchNewItems(newList)
        updateCallback?.let { diffResult.dispatchUpdatesTo(it) }
    }

    private fun latchNewItems(newItems: List<T>) {
        items = newItems
        readOnlyItems = Collections.unmodifiableList(newItems)
    }
}