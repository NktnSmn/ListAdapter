package com.nktnsmn.listadapter.listitems

import android.support.annotation.AnyThread
import com.nktnsmn.listadapter.listitems.observer.ListItemsObserver

interface ObservableListItems<ITEM : Any> : ListItems<ITEM> {

    @AnyThread
    fun holdObserver(observer: ListItemsObserver)

    @AnyThread
    fun releaseObserver()
}