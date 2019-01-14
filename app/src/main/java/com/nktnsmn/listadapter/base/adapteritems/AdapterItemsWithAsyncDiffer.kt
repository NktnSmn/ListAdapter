package com.nktnsmn.listadapter.base.adapteritems

import android.support.v7.recyclerview.extensions.AsyncDifferConfig
import android.support.v7.recyclerview.extensions.AsyncListDiffer
import android.support.v7.util.AdapterListUpdateCallback
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView

class AdapterItemsWithAsyncDiffer<T>(
    private val diffItemCallback: DiffUtil.ItemCallback<T>,
    private val asyncDifferConfig: AsyncDifferConfig<T> = AsyncDifferConfig.Builder(diffItemCallback).build()
) : AdapterItems<T>() {

    private var asyncListDiffer: AsyncListDiffer<T>? = null
    private var missedItems: List<T>? = null

    override fun attachAdapter(adapter: RecyclerView.Adapter<*>) {
        super.attachAdapter(adapter)
        asyncListDiffer = AsyncListDiffer(AdapterListUpdateCallback(adapter), asyncDifferConfig)
        missedItems?.let { missedItems ->
            setWithDiff(missedItems)
            this.missedItems = null
        }
    }

    override fun detachAdapter() {
        asyncListDiffer = null
        super.detachAdapter()
    }

    override fun getAll(): List<T> = asyncListDiffer?.currentList ?: missedItems ?: emptyList()

    fun setWithDiff(items: List<T>) {
        asyncListDiffer.let { asyncListDiffer ->
            if (asyncListDiffer != null) {
                asyncListDiffer.submitList(items)
            } else {
                missedItems = items
            }
        }
    }
}