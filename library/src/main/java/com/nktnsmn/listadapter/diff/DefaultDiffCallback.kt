package com.nktnsmn.listadapter.diff

import androidx.recyclerview.widget.DiffUtil
import com.nktnsmn.listadapter.diff.item.ItemIdModel

open class DefaultDiffCallback<ITEM : Any, NEW_ITEMS : List<ITEM>>(
    protected val oldItems: List<ITEM>,
    protected val newItems: NEW_ITEMS,
    protected val diffItemCallback: DiffUtil.ItemCallback<ITEM>
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

fun diffCallback(
    oldItems: List<ItemIdModel>,
    newItems: List<ItemIdModel>,
    diffItemCallback: DiffUtil.ItemCallback<ItemIdModel> = diffItemCallback()
): DefaultDiffCallback<ItemIdModel, List<ItemIdModel>> =
    DefaultDiffCallback(oldItems, newItems, diffItemCallback)