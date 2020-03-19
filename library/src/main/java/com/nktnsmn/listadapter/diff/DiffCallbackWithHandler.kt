package com.nktnsmn.listadapter.diff

import androidx.recyclerview.widget.DiffUtil
import com.nktnsmn.listadapter.diff.item.SameItemConversionHandler

class DiffCallbackWithHandler<ITEM : Any, NEW_ITEMS : List<ITEM>>(
    oldItems: List<ITEM>,
    newItems: NEW_ITEMS,
    diffItemCallback: DiffUtil.ItemCallback<ITEM>,
    private val sameItemConversionHandler: SameItemConversionHandler<ITEM, NEW_ITEMS>
) : BaseDiffCallback<ITEM, NEW_ITEMS>(oldItems, newItems, diffItemCallback) {

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem: ITEM = oldItems[oldItemPosition]
        val newItem: ITEM = newItems[newItemPosition]
        val isSame = oldItem == newItem
        if (isSame) {
            sameItemConversionHandler.handleItemConversion(
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