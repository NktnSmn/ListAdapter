package com.nktnsmn.listadapter.cellular.itemviewcell

import android.util.SparseArray
import com.nktnsmn.listadapter.viewholder.ViewHolder

class ItemViewCells<ITEM : Any, VH : ViewHolder<ITEM>>(itemViewCells: Collection<ItemViewCell<ITEM, VH>>) {

    private val itemViewCells = SparseArray<ItemViewCell<ITEM, VH>>(itemViewCells.size)

    constructor(vararg itemViewCells: ItemViewCell<ITEM, VH>) : this(itemViewCells.asList())

    init {
        itemViewCells.forEach { this.itemViewCells.put(it.viewType, it) }
    }

    fun getForItem(item: ITEM): ItemViewCell<ITEM, VH> {
        for (i in 0 until itemViewCells.size()) {
            val itemViewCell = itemViewCells.valueAt(i)
            if (itemViewCell.isSuitableForItem(item)) {
                return itemViewCell
            }
        }
        throw IllegalStateException("Cell for item $item not found")
    }

    fun getForViewType(viewType: Int): ItemViewCell<ITEM, VH> =
        itemViewCells.get(viewType) ?: throw IllegalStateException("Cell with view type $viewType not found")

}