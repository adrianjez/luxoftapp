package com.luxoft.sample_app.presentation.base

import androidx.fragment.app.Fragment
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    abstract val containerId: Int

    fun show(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction
                .replace(containerId, fragment)
                .commit()
    }

}
