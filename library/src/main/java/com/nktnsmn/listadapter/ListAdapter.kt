package com.nktnsmn.listadapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.nktnsmn.listadapter.listitems.ListItems
import com.nktnsmn.listadapter.listitems.impl.StableListItems
import com.nktnsmn.listadapter.viewholder.ViewHolder

abstract class ListAdapter<ITEM : Any, VH : ViewHolder<ITEM>> : RecyclerView.Adapter<VH>() {

    var items: ListItems<ITEM> = StableListItems()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = items.size()

    override fun onViewAttachedToWindow(viewHolder: VH) {
        viewHolder.onAttachedToWindow()
    }

    override fun onViewDetachedFromWindow(viewHolder: VH) {
        viewHolder.onDetachedFromWindow()
    }

    override fun onViewRecycled(viewHolder: VH) {
        viewHolder.onRecycled()
    }

    override fun onFailedToRecycleView(viewHolder: VH): Boolean = viewHolder.onFailedToRecycleView()

    override fun onBindViewHolder(viewHolder: VH, position: Int) {
        viewHolder.bindData(items[position])
    }

    override fun onBindViewHolder(viewHolder: VH, position: Int, payloads: List<Any>) {
        viewHolder.bindData(items[position], payloads)
    }
}