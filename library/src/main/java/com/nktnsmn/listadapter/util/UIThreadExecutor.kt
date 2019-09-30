package com.nktnsmn.listadapter.util

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor

internal class UIThreadExecutor : Executor {

    private val mHandler = Handler(Looper.getMainLooper())

    override fun execute(command: Runnable) {
        mHandler.post(command)
    }
}