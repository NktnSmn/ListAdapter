package com.nktnsmn.listadapter.cellular

import com.nktnsmn.listadapter.base.ListAdapter
import com.nktnsmn.listadapter.base.item.IdentifiableItem
import com.nktnsmn.listadapter.items.AdapterItems
import com.nktnsmn.listadapter.cellular.itemviewcell.ItemViewCell
import com.nktnsmn.listadapter.cellular.itemviewcell.ItemViewCells
import com.nktnsmn.listadapter.viewholder.BindableViewHolder

abstract class CellularListAdapter<AI : AdapterItems<IdentifiableItem>, VH : BindableViewHolder>(
    items: AI,
    protected val itemViewCells: ItemViewCells
) : ListAdapter<AI, VH>(items) {

    override fun getItemViewType(position: Int): Int = itemViewCells.getForItem(items[position]).viewType

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        val itemViewCell = itemViewCells.getForItem(item)
        onBindViewHolder(holder, item, itemViewCell)
    }

    protected fun onBindViewHolder(holder: VH, item: Any, itemViewCell: ItemViewCell) {
        holder.bind(itemViewCell.itemBindingVariableId, item)
    }
}