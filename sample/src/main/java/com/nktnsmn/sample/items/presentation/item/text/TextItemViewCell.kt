package com.nktnsmn.sample.items.presentation.item.text

import com.nktnsmn.listadapter.cellular.itemviewcell.BindingItemViewCell
import com.nktnsmn.listadapter.viewholder.BindingViewHolder
import com.nktnsmn.sample.R
import com.nktnsmn.sample.databinding.TextListItemBinding

class TextItemViewCell :
    BindingItemViewCell<TextItemVM, TextListItemBinding, BindingViewHolder<TextItemVM, TextListItemBinding>>(R.layout.text_list_item) {

    override fun isSuitableForItem(item: Any): Boolean = item is TextItemVM

    override fun createBindingViewHolder(
        viewDataBinding: TextListItemBinding
    ): BindingViewHolder<TextItemVM, TextListItemBinding> =
        object : BindingViewHolder<TextItemVM, TextListItemBinding>(viewDataBinding) {
            override fun bindData(data: TextItemVM) {
                viewDataBinding.content = data.content
            }
        }
}