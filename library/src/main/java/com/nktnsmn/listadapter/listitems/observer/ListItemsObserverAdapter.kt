package com.nktnsmn.listadapter.listitems.observer

import android.support.v7.widget.RecyclerView

class ListItemsObserverAdapter(private val adapter: RecyclerView.Adapter<*>) : ListItemsObserver {

    override fun onChangedFully() {
        adapter.notifyDataSetChanged()
    }

    override fun onChanged(position: Int, count: Int, payload: Any?) {
        adapter.notifyItemRangeChanged(position, count, payload)
    }

    override fun onInserted(position: Int, count: Int) {
        adapter.notifyItemRangeInserted(position, count)
    }

    override fun onMoved(fromPosition: Int, toPosition: Int) {
        adapter.notifyItemMoved(fromPosition, toPosition)
    }

    override fun onRemoved(position: Int, count: Int) {
        adapter.notifyItemRangeRemoved(position, count)
    }
}