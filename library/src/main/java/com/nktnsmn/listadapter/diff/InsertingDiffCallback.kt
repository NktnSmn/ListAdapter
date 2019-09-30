package com.nktnsmn.listadapter.diff

import android.support.v7.util.DiffUtil

internal class InsertingDiffCallback<ITEM>(
    oldItems: List<ITEM>,
    private val newItems: MutableList<ITEM>,
    diffItemCallback: DiffUtil.ItemCallback<ITEM>
) : DiffCallback<ITEM>(oldItems, newItems, diffItemCallback) {

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem: ITEM = getOldItem(oldItemPosition)
        val isSame = super.areContentsTheSame(oldItem, getNewItem(newItemPosition))
        if (isSame) {
            newItems[newItemPosition] = oldItem
        }
        return isSame
    }
}