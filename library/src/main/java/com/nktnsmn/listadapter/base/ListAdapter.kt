package com.nktnsmn.listadapter.base

import android.support.v7.widget.RecyclerView
import com.nktnsmn.listadapter.base.listitems.ListItems
import com.nktnsmn.listadapter.base.listitems.impl.EmptyListItems
import com.nktnsmn.listadapter.base.viewholder.ViewHolder

abstract class ListAdapter<T, VH : ViewHolder> : RecyclerView.Adapter<VH>() {

    var items: ListItems<out T> = EmptyListItems
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = items.size()

    override fun onViewAttachedToWindow(holder: VH) {
        super.onViewAttachedToWindow(holder)
        holder.onAttachedToWindow()
    }

    override fun onViewDetachedFromWindow(holder: VH) {
        super.onViewDetachedFromWindow(holder)
        holder.onDetachedFromWindow()
    }

    override fun onViewRecycled(holder: VH) {
        super.onViewRecycled(holder)
        holder.onRecycled()
    }

    override fun onFailedToRecycleView(holder: VH): Boolean = holder.onFailedToRecycleView()
}