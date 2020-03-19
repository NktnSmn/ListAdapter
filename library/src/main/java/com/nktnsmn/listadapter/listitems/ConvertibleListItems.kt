package com.nktnsmn.listadapter.listitems

import androidx.annotation.AnyThread
import androidx.recyclerview.widget.DiffUtil

interface ConvertibleListItems<ITEM : Any> : ListItems<ITEM> {

    @AnyThread
    fun convertTo(
        newItems: List<ITEM>,
        createDiffCallback: (oldItems: List<ITEM>, diffItemCallback: DiffUtil.ItemCallback<ITEM>) -> DiffUtil.Callback
    )
}