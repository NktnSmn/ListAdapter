package com.nktnsmn.sample.imagefeed.ui

import com.nktnsmn.listadapter.base.item.IdentifiableItem
import com.nktnsmn.listadapter.base.listitems.impl.base.BaseUpdatableListItems
import com.nktnsmn.sample.base.BasePresenter
import com.nktnsmn.sample.imagefeed.ui.listitem.ImageListItemClickListener

interface ImageFeedView {

    fun setupImageFeedItems(imageFeedItems: BaseUpdatableListItems<IdentifiableItem>)

    fun hideRefresher()

    fun showToast(message: String)
}

interface ImageFeedPresenter : BasePresenter<ImageFeedView>, ImageListItemClickListener {

    fun refreshImageFeed()
}