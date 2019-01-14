package com.nktnsmn.listadapter.base

import android.support.v7.widget.RecyclerView
import com.nktnsmn.listadapter.base.adapteritems.AdapterItems
import com.nktnsmn.listadapter.base.viewholder.ViewHolder

abstract class ListAdapter<AI : AdapterItems<*>, VH : ViewHolder>(
    val items: AI
) : RecyclerView.Adapter<VH>() {

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        items.attachAdapter(this)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        items.detachAdapter()
        super.onDetachedFromRecyclerView(recyclerView)
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