package com.nktnsmn.listadapter.diff

import androidx.recyclerview.widget.DiffUtil

abstract class BaseDiffCallback<ITEM : Any, NEW_ITEMS : List<ITEM>>(
    protected val oldItems: List<ITEM>,
    protected val newItems: NEW_ITEMS,
    private val diffItemCallback: DiffUtil.ItemCallback<ITEM>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        diffItemCallback.areItemsTheSame(oldItems[oldItemPosition], newItems[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        diffItemCallback.areContentsTheSame(oldItems[oldItemPosition], newItems[newItemPosition])

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? =
        diffItemCallback.getChangePayload(oldItems[oldItemPosition], newItems[newItemPosition])
}