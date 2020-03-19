package com.nktnsmn.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nktnsmn.sample.items.presentation.ItemsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(android.R.id.content, ItemsFragment(), ItemsFragment::class.java.canonicalName)
                .commit()
        }
    }
}
