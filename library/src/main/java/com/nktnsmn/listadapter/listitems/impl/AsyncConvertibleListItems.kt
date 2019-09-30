package com.nktnsmn.listadapter.listitems.impl

import android.support.annotation.UiThread
import android.support.v7.util.DiffUtil
import com.nktnsmn.listadapter.diff.AsyncListDiffer
import com.nktnsmn.listadapter.listitems.ConvertibleListItems
import java.util.*

class AsyncConvertibleListItems<ITEM : Any>(
    diffItemCallback: DiffUtil.ItemCallback<ITEM>
) : BaseObservableListItems<ITEM>(),
    ConvertibleListItems<ITEM>,
    AsyncListDiffer.OnDiffCompletedCallback<ITEM> {

    private var asyncListDiffer: AsyncListDiffer<ITEM> =
        AsyncListDiffer(diffItemCallback, this)
    private var items: List<ITEM> = emptyList()
    private var readOnlyItems: List<ITEM> = Collections.unmodifiableList(emptyList())

    override fun getAll(): List<ITEM> = readOnlyItems

    override fun convertTo(newItems: List<ITEM>) {
        when {
            newItems.isEmpty() -> {
                val removedCount = size()
                runOnUI {
                    latchNewItems(emptyList())
                    observer?.onRemoved(0, removedCount)
                }
            }
            items.isEmpty()    -> {
                runOnUI {
                    latchNewItems(newItems)
                    observer?.onInserted(0, newItems.size)
                }
            }
            observer == null   -> latchNewItems(newItems)
            else               -> asyncListDiffer.calculateDiffAsync(items, newItems)
        }
    }

    fun release() {
        asyncListDiffer.release()
    }

    @UiThread
    override fun onDiffCompleted(newList: List<ITEM>, diffResult: DiffUtil.DiffResult) {
        latchNewItems(newList)
        observer?.let { updateCallback -> diffResult.dispatchUpdatesTo(updateCallback) }
    }

    private fun latchNewItems(newItems: List<ITEM>) {
        items = newItems
        readOnlyItems = Collections.unmodifiableList(newItems)
    }
}