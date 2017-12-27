package com.lwu.topekalwu.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import android.widget.Checkable
import com.lwu.topekalwu.R
import com.lwu.topekalwu.helper.ApiLevelHelper

/**
 * Created by lwu on 12/13/17.
 *
 * A simple view that wraps an avatar.
 */
class AvatarView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
) : AppCompatImageView(context, attrs, defStyle), Checkable {

    private var isChecked = false

    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.AvatarView, defStyle, 0)
        try {
            val avatarDrawableId = attributes.getResourceId(R.styleable.AvatarView_avatar, NOT_FOUND)
            if (avatarDrawableId != NOT_FOUND) {
                setAvatar(avatarDrawableId)
            }
        } finally {
            attributes.recycle()
        }
    }

    fun setAvatar(@DrawableRes resId: Int) {
        if (ApiLevelHelper.isAtLeast(Build.VERSION_CODES.LOLLIPOP)) {
            clipToOutline = true
            setImageResource(resId)
        } else {
            setAvatarPreLollipop(resId)
        }
    }

    private fun setAvatarPreLollipop(@DrawableRes resId: Int) {
        val drawable = ResourcesCompat.getDrawable(resources, resId, context.theme) as BitmapDrawable
        val roundedDrawable = RoundedBitmapDrawableFactory.create(resources, drawable.bitmap)
                .apply {
                    isCircular = true
                }
        setImageDrawable(roundedDrawable)
    }

    override fun setChecked(b: Boolean) {
        isChecked = b
        invalidate()
    }

    override fun isChecked() = isChecked

    override fun toggle() = setChecked(!isChecked)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (isChecked) {
            ContextCompat.getDrawable(context, R.drawable.selector_avatar).apply {
                setBounds(0, 0, width, height)
                draw(canvas)
            }
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (ApiLevelHelper.isLowerThan(Build.VERSION_CODES.LOLLIPOP)) {
            return
        }
        if (w > 0 && h > 0) {
            outlineProvider = RoundOutlineProvider(Math.min(w, h))
        }
    }

    companion object {
        private val NOT_FOUND = 0
    }

}