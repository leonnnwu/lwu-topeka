package com.lwu.topekalwu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.lwu.topekalwu.R
import com.lwu.topekalwu.model.Avatar
import com.lwu.topekalwu.widget.AvatarView

/**
 * Created by lwu on 12/12/17.
 */
class AvatarAdapter(context: Context) : BaseAdapter() {
    private val layoutInflator = LayoutInflater.from(context)

    override fun getView(position: Int, contentView: View?, parent: ViewGroup?): View {
        return ((contentView ?:
                layoutInflator.inflate(R.layout.item_avatar, parent, false)) as AvatarView)
                .also { setAvatar(it, avatars[position]) }
    }

    private fun setAvatar(view: AvatarView, avatar: Avatar) {
        with(view) {
            setAvatar(avatar.drawableId)
            contentDescription = avatar.nameForAccessibility
        }
    }

    override fun getItem(position: Int): Any = avatars[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = avatars.size


    companion object {
        private val avatars = Avatar.values()
    }

}