package com.nktnsmn.sample

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

@Suppress("unused")
class SampleApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}