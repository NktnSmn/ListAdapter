package com.nktnsmn.listadapter.base.diff

import android.support.v7.util.DiffUtil

open class DiffCallback<T>(
    private val oldItems: List<T>,
    private val newItems: List<T>,
    private val diffItemCallback: DiffUtil.ItemCallback<T>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        diffItemCallback.areItemsTheSame(oldItems[oldItemPosition], newItems[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        diffItemCallback.areContentsTheSame(oldItems[oldItemPosition], newItems[newItemPosition])
}