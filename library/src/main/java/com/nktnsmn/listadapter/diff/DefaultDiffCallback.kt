package com.nktnsmn.listadapter.diff

import androidx.recyclerview.widget.DiffUtil
import com.nktnsmn.listadapter.diff.item.IdentifiableByAnyItem

internal class DefaultDiffCallback<ITEM : Any>(
    oldItems: List<ITEM>,
    newItems: List<ITEM>,
    diffItemCallback: DiffUtil.ItemCallback<ITEM>
) : BaseDiffCallback<ITEM, List<ITEM>>(oldItems, newItems, diffItemCallback)

fun diffCallback(
    oldItems: List<IdentifiableByAnyItem>,
    newItems: List<IdentifiableByAnyItem>,
    diffItemCallback: DiffUtil.ItemCallback<IdentifiableByAnyItem> = diffItemCallback()
): BaseDiffCallback<IdentifiableByAnyItem, List<IdentifiableByAnyItem>> =
    DefaultDiffCallback(oldItems, newItems, diffItemCallback)