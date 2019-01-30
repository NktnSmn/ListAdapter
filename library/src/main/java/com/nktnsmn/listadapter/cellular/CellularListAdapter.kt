package com.nktnsmn.listadapter.cellular

import com.nktnsmn.listadapter.base.ListAdapter
import com.nktnsmn.listadapter.base.item.IdentifiableItem
import com.nktnsmn.listadapter.base.viewholder.BindableViewHolder
import com.nktnsmn.listadapter.cellular.itemviewcell.ItemViewCell
import com.nktnsmn.listadapter.cellular.itemviewcell.ItemViewCells

abstract class CellularListAdapter<VH : BindableViewHolder>(
    protected val itemViewCells: ItemViewCells
) : ListAdapter<IdentifiableItem, VH>() {

    constructor(vararg itemViewCells: ItemViewCell) : this(ItemViewCells(*itemViewCells))

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