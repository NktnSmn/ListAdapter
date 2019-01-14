package com.nktnsmn.listadapter.base.diff

import android.support.v7.util.DiffUtil
import com.nktnsmn.listadapter.base.diff.item.UniversalDiffItemCallback
import com.nktnsmn.listadapter.base.item.IdentifiableItem

class UniversalDiffCallback(
    oldItems: List<IdentifiableItem>,
    newItems: List<IdentifiableItem>,
    diffItemCallback: DiffUtil.ItemCallback<IdentifiableItem> = UniversalDiffItemCallback()
) : DiffCallback<IdentifiableItem>(oldItems, newItems, diffItemCallback)