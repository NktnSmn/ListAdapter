package com.nktnsmn.sample.items.presentation.item.text

import com.nktnsmn.listadapter.cellular.itemviewcell.impl.BaseBindingItemViewCell
import com.nktnsmn.listadapter.viewholder.BaseBindingViewHolder
import com.nktnsmn.sample.R
import com.nktnsmn.sample.databinding.TextListItemBinding

class TextItemViewCell : BaseBindingItemViewCell<TextListItemBinding, BaseBindingViewHolder<*, TextListItemBinding>>(
    R.layout.text_list_item,
    TextItemVM::class.java,
    R.layout.text_list_item
) {

    override fun createBindingViewHolder(
        viewDataBinding: TextListItemBinding
    ): BaseBindingViewHolder<*, TextListItemBinding> =
        object : BaseBindingViewHolder<TextItemVM, TextListItemBinding>(viewDataBinding) {
            override fun bindDataInternal(data: TextItemVM) {
                viewDataBinding.content = data.content
            }
        }
}