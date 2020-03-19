package com.nktnsmn.listadapter

import androidx.recyclerview.widget.RecyclerView
import com.nktnsmn.listadapter.listitems.ListItems
import com.nktnsmn.listadapter.listitems.impl.StableListItems
import com.nktnsmn.listadapter.viewholder.ViewHolder

abstract class ListAdapter<T : Any, VH : ViewHolder<*>> : RecyclerView.Adapter<VH>() {

    var items: ListItems<out T> = StableListItems()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = items.size()

    override fun onViewAttachedToWindow(viewHolder: VH) {
        super.onViewAttachedToWindow(viewHolder)
        viewHolder.onAttachedToWindow()
    }

    override fun onViewDetachedFromWindow(viewHolder: VH) {
        super.onViewDetachedFromWindow(viewHolder)
        viewHolder.onDetachedFromWindow()
    }

    override fun onViewRecycled(viewHolder: VH) {
        super.onViewRecycled(viewHolder)
        viewHolder.onRecycled()
    }

    override fun onFailedToRecycleView(viewHolder: VH): Boolean =
        viewHolder.onFailedToRecycleView()

    override fun onBindViewHolder(viewHolder: VH, position: Int) {
        viewHolder.bindData(items[position])
    }

    override fun onBindViewHolder(viewHolder: VH, position: Int, payloads: MutableList<Any>) {
        viewHolder.bindData(items[position], payloads)
    }
}