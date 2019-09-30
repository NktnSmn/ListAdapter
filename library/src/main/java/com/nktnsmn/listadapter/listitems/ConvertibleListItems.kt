package com.nktnsmn.listadapter.listitems

import android.support.annotation.AnyThread

interface ConvertibleListItems<ITEM : Any> : ObservableListItems<ITEM> {

    @AnyThread
    fun convertTo(newItems: List<ITEM>)
}