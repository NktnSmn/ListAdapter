package com.nktnsmn.listadapter.cellular.itemviewcell

import android.util.SparseArray
import com.nktnsmn.listadapter.viewholder.ViewHolder

class ItemViewCells<out VH : ViewHolder<*>>(vararg itemViewCells: ItemViewCell<out VH>) {

    private val itemViewCells = SparseArray<ItemViewCell<out VH>>(itemViewCells.size)
    private var lastUsedItemViewCell: ItemViewCell<out VH>? = null

    init {
        itemViewCells.forEach { cell -> this.itemViewCells.put(cell.viewType, cell) }
    }

    fun getForItem(item: Any): ItemViewCell<out VH> {
        lastUsedItemViewCell?.let { lastUsedItemViewCell ->
            if (lastUsedItemViewCell.isSuitableForItem(item)) {
                return lastUsedItemViewCell
            } else {
                this.lastUsedItemViewCell = null
            }
        }
        for (i in 0 until itemViewCells.size()) {
            val itemViewCell = itemViewCells.valueAt(i)
            if (itemViewCell.isSuitableForItem(item)) {
                lastUsedItemViewCell = itemViewCell
                return itemViewCell
            }
        }
        throw Exception("Ячейка для элемента $item не найдена")
    }

    fun getForViewType(viewType: Int): ItemViewCell<out VH> =
        itemViewCells.get(viewType).also { itemViewCell ->
            lastUsedItemViewCell = itemViewCell
        }
            ?: throw Exception("Ячейка с типом вью $viewType не найдена")

}