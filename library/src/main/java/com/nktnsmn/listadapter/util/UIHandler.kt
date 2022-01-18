package com.nktnsmn.listadapter.util

import android.os.Handler
import android.os.Looper
import androidx.annotation.AnyThread
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

object UIHandler : Handler(Looper.getMainLooper())

class UiThreadVar<T : Any> : ReadWriteProperty<Any?, T?> {

    private var value: T? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): T? = value

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        checkUiThread()
        this.value = value
    }
}

fun isUiThread(): Boolean = Looper.myLooper() == Looper.getMainLooper()

@AnyThread
fun checkUiThread() {
    check(isUiThread()) { "Method must be called on the UI thread" }
}

@AnyThread
fun runOnUI(block: () -> Unit) {
    if (isUiThread()) {
        block()
    } else {
        UIHandler.post(block)
    }
}

