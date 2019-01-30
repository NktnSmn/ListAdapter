package com.nktnsmn.sample.base

import android.support.annotation.UiThread

@UiThread
class NullableView<VIEW> {

    var view: VIEW? = null
        private set

    fun set(view: VIEW) {
        this.view = view
    }

    fun reset() {
        this.view = null
    }

    inline fun action(crossinline action: VIEW.() -> Unit): Boolean {
        action(view ?: return false)
        return true
    }
}