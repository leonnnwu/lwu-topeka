package com.lwu.topekalwu.widget

import android.annotation.TargetApi
import android.graphics.Outline
import android.os.Build
import android.view.View
import android.view.ViewOutlineProvider

/**
 * Created by lwu on 12/27/17.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class RoundOutlineProvider(private val size: Int): ViewOutlineProvider() {

    init {
        if (0 > size) throw IllegalArgumentException("size needs to be > 0. Actually was " + size)
    }

    override fun getOutline(view: View, outline: Outline) = outline.setOval(0,0, size, size)
}