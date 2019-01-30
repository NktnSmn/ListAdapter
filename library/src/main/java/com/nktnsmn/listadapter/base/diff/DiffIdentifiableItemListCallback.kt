package com.nktnsmn.listadapter.base.diff

import android.support.v7.util.DiffUtil
import com.nktnsmn.listadapter.base.diff.item.DiffIdentifiableItemCallback
import com.nktnsmn.listadapter.base.item.IdentifiableItem

class DiffIdentifiableItemListCallback(
    oldItems: List<IdentifiableItem>,
    newItems: List<IdentifiableItem>,
    diffItemCallback: DiffUtil.ItemCallback<IdentifiableItem> = DiffIdentifiableItemCallback()
) : DiffCallback<IdentifiableItem>(oldItems, newItems, diffItemCallback)