package com.nktnsmn.listadapter.base.adapteritems

import android.support.v7.util.DiffUtil
import com.nktnsmn.listadapter.base.diff.DiffCallback

open class AdapterItemsWithDiffer<T>(
    private val diffItemCallback: DiffUtil.ItemCallback<T>
) : MutableAdapterItems<T>() {

    fun setWithDiff(items: List<T>) {
        adapter.let { adapter ->
            if (adapter != null) {
                val diffResult = DiffUtil.calculateDiff(DiffCallback(getAll(), items, diffItemCallback))
                set(items)
                diffResult.dispatchUpdatesTo(adapter)
            } else {
                set(items)
            }
        }
    }
}