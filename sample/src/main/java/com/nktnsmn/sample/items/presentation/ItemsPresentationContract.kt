package com.nktnsmn.sample.items.presentation

import com.nktnsmn.listadapter.diff.item.IdentifiableByAnyItem
import com.nktnsmn.listadapter.listitems.ListItems
import com.nktnsmn.sample.base.BasePresenter
import com.nktnsmn.sample.items.presentation.item.ImageItemClickListener

interface ItemsView {

    fun setupItems(items: ListItems<IdentifiableByAnyItem>)

    fun hideRefresher()

    fun showToast(message: String)
}

interface ItemsPresenter : BasePresenter<ItemsView>, ImageItemClickListener {

    fun refreshItems()
}