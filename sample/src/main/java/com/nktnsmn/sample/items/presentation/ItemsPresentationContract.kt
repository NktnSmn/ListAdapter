package com.nktnsmn.sample.items.presentation

import com.nktnsmn.listadapter.diff.item.IdentifiableByAnyItem
import com.nktnsmn.listadapter.listitems.impl.BaseObservableListItems
import com.nktnsmn.sample.base.BasePresenter
import com.nktnsmn.sample.items.presentation.item.ImageItemClickListener

interface ItemsView {

    fun setupItems(items: BaseObservableListItems<IdentifiableByAnyItem>)

    fun hideRefresher()

    fun showToast(message: String)
}

interface ItemsPresenter : BasePresenter<ItemsView>, ImageItemClickListener {

    fun refreshItems()
}