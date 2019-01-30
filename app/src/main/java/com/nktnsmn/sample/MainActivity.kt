package com.nktnsmn.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nktnsmn.sample.imagefeed.ui.ImageFeedFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(android.R.id.content,
                    ImageFeedFragment(), ImageFeedFragment::class.java.canonicalName)
                .commit()
        }
    }
}
