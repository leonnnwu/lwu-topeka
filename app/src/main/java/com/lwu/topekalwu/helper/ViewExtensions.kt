package com.lwu.topekalwu.helper

import android.view.View

/**
 * Created by lwu on 12/27/17.
 */
inline fun View.onLayoutChange(crossinline action: View.() -> Unit) {
    addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
        override fun onLayoutChange(v: View?,
                                    left: Int, top: Int, right: Int, bottom: Int,
                                    oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
            removeOnLayoutChangeListener(this)
            action()
        }
    })
}