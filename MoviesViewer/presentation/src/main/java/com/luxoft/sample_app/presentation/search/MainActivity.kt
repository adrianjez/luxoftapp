package com.luxoft.sample_app.presentation.search

import android.os.Bundle
import com.luxoft.sample_app.R
import dagger.android.support.DaggerAppCompatActivity



class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
