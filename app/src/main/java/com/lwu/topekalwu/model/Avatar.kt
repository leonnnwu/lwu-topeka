package com.lwu.topekalwu.model

import android.support.annotation.DrawableRes
import com.lwu.topekalwu.R

/**
 * Created by lwu on 12/13/17.
 */
enum class Avatar(@param:DrawableRes val drawableId: Int) {

    ONE(R.drawable.avatar_1),
    TWO(R.drawable.avatar_2),
    THREE(R.drawable.avatar_3),
    FOUR(R.drawable.avatar_4),
    FIVE(R.drawable.avatar_5),
    SIX(R.drawable.avatar_6),
    SEVEN(R.drawable.avatar_7),
    EIGHT(R.drawable.avatar_8),
    NINE(R.drawable.avatar_9),
    TEN(R.drawable.avatar_10),
    ELEVEN(R.drawable.avatar_11),
    TWELVE(R.drawable.avatar_12),
    THIRTEEN(R.drawable.avatar_13),
    FOURTEEN(R.drawable.avatar_14),
    FIFTEEN(R.drawable.avatar_15),
    SIXTEEN(R.drawable.avatar_16);

    val nameForAccessibility get() = "Avatar ${ordinal + 1}"
}