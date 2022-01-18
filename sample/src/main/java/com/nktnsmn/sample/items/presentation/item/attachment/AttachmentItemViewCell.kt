package com.nktnsmn.sample.items.presentation.item.attachment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.nktnsmn.listadapter.cellular.itemviewcell.ItemViewCell
import com.nktnsmn.listadapter.viewholder.ViewHolder
import com.nktnsmn.sample.R

class AttachmentItemViewCell : ItemViewCell<AttachmentItemVM, AttachmentItemViewHolder> {

    override val viewType: Int get() = R.layout.attachment_list_item

    override fun isSuitableForItem(item: Any): Boolean = item is AttachmentItemVM

    override fun createViewHolder(parent: ViewGroup): AttachmentItemViewHolder {
        val rootView: View = LayoutInflater.from(parent.context).inflate(R.layout.attachment_list_item, parent, false)
        return AttachmentItemViewHolder(
            rootView,
            rootView.findViewById(R.id.attachment_icon_view),
            rootView.findViewById(R.id.attachment_name_view)
        )
    }
}

class AttachmentItemViewHolder(
    rootView: View,
    private val iconView: ImageView,
    private val nameView: TextView
) : ViewHolder<AttachmentItemVM>(rootView) {

    override fun bindData(data: AttachmentItemVM) {
        iconView.setImageResource(data.iconResId)
        iconView.setColorFilter(data.iconColor)
        nameView.text = data.name
    }
}