package com.luxoft.sample_app.utils

import android.os.CountDownTimer
import androidx.appcompat.widget.SearchView

fun SearchView.afterTextChangedDelayed(afterTextChanged: (String) -> Unit) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        var timer: CountDownTimer? = null

        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            timer?.cancel()
            timer = object : CountDownTimer(500, 1000) {
                override fun onTick(millisUntilFinished: Long) {}
                override fun onFinish() {
                    afterTextChanged.invoke(newText.toString())
                }
            }.start()
            return false
        }
    })
}

