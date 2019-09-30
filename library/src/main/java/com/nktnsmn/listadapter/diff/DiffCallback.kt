package com.nktnsmn.listadapter.diff

import android.support.v7.util.DiffUtil

open class DiffCallback<ITEM>(
    private val oldItems: List<ITEM>,
    private val newItems: List<ITEM>,
    private val diffItemCallback: DiffUtil.ItemCallback<ITEM>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        areItemsTheSame(getOldItem(oldItemPosition), getNewItem(newItemPosition))

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        areContentsTheSame(getOldItem(oldItemPosition), getNewItem(newItemPosition))

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? =
        getChangePayload(getOldItem(oldItemPosition), getNewItem(newItemPosition))

    protected fun areItemsTheSame(oldItem: ITEM, newItem: ITEM): Boolean =
        diffItemCallback.areItemsTheSame(oldItem, newItem)

    protected fun areContentsTheSame(oldItem: ITEM, newItem: ITEM): Boolean =
        diffItemCallback.areContentsTheSame(oldItem, newItem)

    protected fun getChangePayload(oldItem: ITEM, newItem: ITEM): Any? =
        diffItemCallback.getChangePayload(oldItem, newItem)

    protected fun getOldItem(position: Int): ITEM = oldItems[position]

    protected fun getNewItem(position: Int): ITEM = newItems[position]
}