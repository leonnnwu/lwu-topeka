package com.lwu.topekalwu.helper

import android.annotation.SuppressLint
import android.content.Context
import com.lwu.topekalwu.model.Avatar
import com.lwu.topekalwu.model.Player

/**
 * Created by lwu on 12/10/17.
 */
const val PLAYER_PREFERENCES = "playerPreferences"
const val PREFERENCE_FIRST_NAME = "$PLAYER_PREFERENCES.firstName"
const val PREFERENCE_LAST_INITIAL = "$PLAYER_PREFERENCES.lastInitial"
const val PREFERENCE_AVATAR = "$PLAYER_PREFERENCES.avatar"

@SuppressLint("CommitPrefEdits")
private fun Context.editPlayer() = getPlayerPreferences().edit()

private fun Context.getPlayerPreferences() =
        getSharedPreferences(PLAYER_PREFERENCES, Context.MODE_PRIVATE)

fun Context.signOut() {
    editPlayer().clear().commit()
    //TODO clear database
}

fun Context.isSignedIn(): Boolean = with(getPlayerPreferences()) {
    contains(PREFERENCE_FIRST_NAME)
            && contains(PREFERENCE_LAST_INITIAL)
            && contains(PREFERENCE_AVATAR)
}

fun Context.getPlayer() = with(getPlayerPreferences()) {
    Player(getString(PREFERENCE_FIRST_NAME, null),
            getString(PREFERENCE_LAST_INITIAL, null),
            getString(PREFERENCE_AVATAR, null)
                    ?.let { Avatar.valueOf(it) })
}

fun Context.savePlayer(player: Player) {
    with(player) {
        if (valid()) {
            editPlayer()
                    .putString(PREFERENCE_FIRST_NAME, firstName)
                    .putString(PREFERENCE_LAST_INITIAL, lastInitial)
                    .putString(PREFERENCE_AVATAR, avatar?.name)
                    .apply()
        }
    }
}