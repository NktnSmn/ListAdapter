package com.nktnsmn.listadapter.listitems.impl

import androidx.recyclerview.widget.DiffUtil
import com.nktnsmn.listadapter.listitems.ConvertibleListItems
import com.nktnsmn.listadapter.observer.ListItemsObserver
import com.nktnsmn.listadapter.util.UiThreadVar
import com.nktnsmn.listadapter.util.runOnUI
import java.util.concurrent.atomic.AtomicInteger

class ConvertibleListItemsImpl<ITEM : Any>(
    private val diffItemCallback: DiffUtil.ItemCallback<ITEM>
) : ConvertibleListItems<ITEM> {

    override var observer: ListItemsObserver? by UiThreadVar()
    private var items: List<ITEM> = emptyList()
    private var maxDiffNumber = AtomicInteger(0)

    override fun getAll(): List<ITEM> = items

    override fun convertTo(
        newItems: List<ITEM>,
        createDiffCallback: (oldItems: List<ITEM>, diffItemCallback: DiffUtil.ItemCallback<ITEM>) -> DiffUtil.Callback
    ) {
        val currentItems: List<ITEM> = items
        when {
            currentItems === newItems -> return
            currentItems.isEmpty() -> runOnUI {
                items = newItems
                observer?.onInserted(0, newItems.size)
            }
            newItems.isEmpty() -> runOnUI {
                items = newItems
                observer?.onRemoved(0, currentItems.size)
            }
            else -> {
                val diffNumber: Int = maxDiffNumber.incrementAndGet()
                val diffCallback: DiffUtil.Callback = createDiffCallback(getAll(), diffItemCallback)
                val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(diffCallback)
                runOnUI {
                    if (diffNumber == maxDiffNumber.get()) {
                        items = newItems
                        observer?.let { updateCallback -> diffResult.dispatchUpdatesTo(updateCallback) }
                    }
                }
            }
        }
    }
}