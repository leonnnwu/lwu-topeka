package com.lwu.topekalwu.widget

import android.text.Editable
import android.text.TextWatcher

/**
 * Created by lwu on 12/27/17.
 */
object TextWatcherAdapter : TextWatcher {
    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) = Unit

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) = Unit

    override fun afterTextChanged(s: Editable) = Unit
}