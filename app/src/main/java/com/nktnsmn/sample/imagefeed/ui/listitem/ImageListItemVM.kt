package com.nktnsmn.sample.imagefeed.ui.listitem

import com.nktnsmn.listadapter.base.item.IdentifiableItem

data class ImageListItemVM(
    override val id: String,
    val uri: String,
    val title: String,
    val description: String? = null
) : IdentifiableItem