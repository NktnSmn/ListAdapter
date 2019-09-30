package com.nktnsmn.listadapter.diff

import android.support.v7.util.DiffUtil
import com.nktnsmn.listadapter.diff.item.*

object DiffFactory {

    fun diffCallback(
        oldItems: List<IdentifiableByAnyItem>,
        newItems: List<IdentifiableByAnyItem>,
        diffItemCallback: DiffUtil.ItemCallback<IdentifiableByAnyItem> = diffItemCallback()
    ): DiffCallback<IdentifiableByAnyItem> =
        DiffCallback(oldItems, newItems, diffItemCallback)

    fun diffItemCallback(
        itemIdProvider: ItemIdProvider<IdentifiableByAnyItem, Any> = IdentifiableItemIdProvider(),
        itemComparator: ItemComparator<IdentifiableByAnyItem> = ByEqualsItemComparator(),
        changePayloadProvider: ItemChangePayloadProvider<IdentifiableByAnyItem>? = null
    ): DiffItemCallback<IdentifiableByAnyItem, Any> =
        DiffItemCallback(itemIdProvider, itemComparator, changePayloadProvider)

}