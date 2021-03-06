package com.lwu.topekalwu.helper

import android.os.Build

/**
 * Created by lwu on 12/13/17.
 */
object ApiLevelHelper {

    /**
     * Checks if the current api level is at least the provided value.
     *
     * @param apiLevel One of the values within [Build.VERSION_CODES].
     *
     * @return `true` if the calling version is at least `apiLevel`.
     *  Else `false` is returned.
     */
    fun isAtLeast(apiLevel: Int) = Build.VERSION.SDK_INT >= apiLevel

    /**
     * Checks if the current api level is at lower than the provided value.

     * @param apiLevel One of the values within [Build.VERSION_CODES].
     *
     * @return `true` if the calling version is lower than `apiLevel`.
     * Else `false` is returned.
     */
    fun isLowerThan(apiLevel: Int) = Build.VERSION.SDK_INT < apiLevel

}