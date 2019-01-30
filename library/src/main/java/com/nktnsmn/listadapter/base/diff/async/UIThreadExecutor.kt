package com.nktnsmn.listadapter.base.diff.async

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor

internal class UIThreadExecutor : Executor {

    private val mHandler = Handler(Looper.getMainLooper())

    override fun execute(command: Runnable) {
        mHandler.post(command)
    }
}