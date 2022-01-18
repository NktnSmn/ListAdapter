package com.nktnsmn.listadapter.diff

import androidx.recyclerview.widget.DiffUtil
import com.nktnsmn.listadapter.diff.item.DiffSameItemHandler

class DiffCallbackWithHandler<ITEM : Any, NEW_ITEMS : List<ITEM>>(
    oldItems: List<ITEM>,
    newItems: NEW_ITEMS,
    diffItemCallback: DiffUtil.ItemCallback<ITEM>,
    private val sameItemHandler: DiffSameItemHandler<ITEM, NEW_ITEMS>
) : DefaultDiffCallback<ITEM, NEW_ITEMS>(oldItems, newItems, diffItemCallback) {

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem: ITEM = oldItems[oldItemPosition]
        val newItem: ITEM = newItems[newItemPosition]
        val isSame = diffItemCallback.areContentsTheSame(oldItem, newItem)
        if (isSame) {
            sameItemHandler.handleItemConversion(
                oldItems,
                oldItemPosition,
                oldItem,
                newItems,
                newItemPosition,
                newItem
            )
        }
        return isSame
    }
}