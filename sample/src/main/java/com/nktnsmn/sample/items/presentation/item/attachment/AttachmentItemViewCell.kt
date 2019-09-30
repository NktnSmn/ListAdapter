package com.nktnsmn.sample.items.presentation.item.attachment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.nktnsmn.listadapter.cellular.itemviewcell.impl.BaseItemViewCell
import com.nktnsmn.listadapter.viewholder.ViewHolder
import com.nktnsmn.sample.R

class AttachmentItemViewCell : BaseItemViewCell<AttachmentItemViewHolder>(
    R.layout.attachment_list_item,
    AttachmentItemVM::class.java
) {

    override fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup): AttachmentItemViewHolder {
        val rootView: View = inflater.inflate(R.layout.attachment_list_item, parent, false)
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

    override fun bindDataInternal(data: AttachmentItemVM) {
        iconView.setImageResource(data.iconResId)
        iconView.setColorFilter(data.iconColor)
        nameView.text = data.name
    }
}