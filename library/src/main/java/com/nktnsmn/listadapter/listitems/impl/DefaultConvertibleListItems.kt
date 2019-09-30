package com.nktnsmn.listadapter.listitems.impl

import android.support.v7.util.DiffUtil
import com.nktnsmn.listadapter.diff.DiffCallback
import com.nktnsmn.listadapter.listitems.ConvertibleListItems

open class DefaultConvertibleListItems<ITEM : Any>(
    private val diffItemCallback: DiffUtil.ItemCallback<ITEM>
) : DefaultMutableListItems<ITEM>(),
    ConvertibleListItems<ITEM> {

    override fun convertTo(newItems: List<ITEM>) {
        when {
            newItems.isEmpty() -> runOnUI { removeAll() }
            isEmpty()          -> runOnUI { add(newItems) }
            observer == null   -> runOnUI { changeFully(newItems) }
            else               -> {
                val diffResult = DiffUtil.calculateDiff(
                    DiffCallback(
                        getAll(),
                        newItems,
                        diffItemCallback
                    )
                )
                runOnUI {
                    latchItems(newItems)
                    observer?.let { updateCallback -> diffResult.dispatchUpdatesTo(updateCallback) }
                }
            }
        }
    }
}