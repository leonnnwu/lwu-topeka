package com.lwu.topekalwu.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by lwu on 12/27/17.
 */
data class Player(
        val firstName: String?,
        val lastInitial: String?,
        val avatar: Avatar?
) : Parcelable {

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeString(firstName)
            writeString(lastInitial)
            if (avatar != null) writeInt(avatar.ordinal)
        }
    }

    fun valid() = !(firstName.isNullOrEmpty() || lastInitial.isNullOrEmpty()) && avatar != null

    override fun describeContents() = 0

    companion object {

        @JvmField val CREATOR: Parcelable.Creator<Player> = object : Parcelable.Creator<Player> {

            override fun createFromParcel(parcel: Parcel) = with(parcel) {
                Player(firstName = readString(),
                        lastInitial = readString(),
                        avatar = Avatar.values()[readInt()])
            }

            override fun newArray(size: Int): Array<Player?> = arrayOfNulls(size)
        }
    }
}