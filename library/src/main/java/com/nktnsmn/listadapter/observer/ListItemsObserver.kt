package com.nktnsmn.listadapter.observer

import androidx.recyclerview.widget.ListUpdateCallback

interface ListItemsObserver : ListUpdateCallback {

    fun onChangedFully()
}