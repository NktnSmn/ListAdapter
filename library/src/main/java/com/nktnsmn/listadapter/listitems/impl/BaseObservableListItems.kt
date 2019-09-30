package com.nktnsmn.listadapter.listitems.impl

import android.os.Looper
import com.nktnsmn.listadapter.listitems.ObservableListItems
import com.nktnsmn.listadapter.listitems.observer.ListItemsObserver
import com.nktnsmn.listadapter.util.UIThreadExecutor

abstract class BaseObservableListItems<ITEM : Any> : ObservableListItems<ITEM> {

    protected var observer: ListItemsObserver? = null
    private var uiThreadExecutor: UIThreadExecutor? = null

    override fun holdObserver(observer: ListItemsObserver) {
        this.observer = observer
    }

    override fun releaseObserver() {
        this.observer = null
    }

    protected fun runOnUI(block: () -> Unit) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            block()
        } else {
            uiThreadExecutor().execute { block() }
        }
    }

    private fun uiThreadExecutor(): UIThreadExecutor =
        uiThreadExecutor.let { uiThreadExecutor ->
            if (uiThreadExecutor == null) {
                val newUiThreadExecutor = UIThreadExecutor()
                this.uiThreadExecutor = newUiThreadExecutor
                newUiThreadExecutor
            } else {
                uiThreadExecutor
            }
        }

}