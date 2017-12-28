package com.lwu.topekalwu.model

import android.os.Parcel
import android.support.test.espresso.matcher.ViewMatchers.assertThat
import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import org.hamcrest.Matchers.`is`
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by lwu on 12/28/17.
 */

val TEST_FIRST_NAME = "Zaphod"
val TEST_LAST_INITIAL = "B"
val TEST_AVATAR = Avatar.TWELVE

val TEST_PLAYER = Player(TEST_FIRST_NAME, TEST_LAST_INITIAL, TEST_AVATAR)

@SmallTest
@RunWith(AndroidJUnit4::class)
class PlayerAndroidTest {

    @Test
    fun writeToParcel() {
        val dest = Parcel.obtain()
        TEST_PLAYER.writeToParcel(dest, 0)
        dest.setDataPosition(0)
        val unparcelled = Player.CREATOR.createFromParcel(dest)
        assertThat(TEST_PLAYER, `is`(unparcelled))
    }

}