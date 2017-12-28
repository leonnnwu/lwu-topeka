package com.lwu.topekalwu.activity

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import com.lwu.topekalwu.R
import com.lwu.topekalwu.helper.signOut
import com.lwu.topekalwu.model.Avatar
import com.lwu.topekalwu.model.TEST_AVATAR
import com.lwu.topekalwu.model.TEST_FIRST_NAME
import com.lwu.topekalwu.model.TEST_LAST_INITIAL
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by lwu on 12/28/17.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class SignInActivityTest {

    val activityRule @Rule get() = object : ActivityTestRule<SignInActivity>(SignInActivity::class.java) {
        override fun beforeActivityLaunched() {
            InstrumentationRegistry.getTargetContext().signOut()
        }
    }

    @Before fun clearPreferences() {
        InstrumentationRegistry.getTargetContext().signOut()
    }

    @Test fun checkFab_initiallyNotDisplayed() {
        onView(withId(R.id.done)).check(matches(not(isDisplayed())))
    }

    @Test fun signIn_withoutFirstNameFailed() {
        inputData(null, TEST_LAST_INITIAL, TEST_AVATAR)
        onDoneView().check(matches(not(isDisplayed())))
    }

    @Test fun signIn_withoutLastInitialFailed() {
        inputData(TEST_FIRST_NAME, null, TEST_AVATAR)
        onDoneView().check(matches(not(isDisplayed())))
    }

    @Test fun signIn_withoutAvatarFailed() {
        inputData(TEST_FIRST_NAME, TEST_LAST_INITIAL, null)
        onDoneView().check(matches(not(isDisplayed())))
    }

    @Test fun signIn_withAllPlayerPreferencesSuccessfully() {
        inputData(TEST_FIRST_NAME, TEST_LAST_INITIAL, TEST_AVATAR)
        onDoneView().check(matches(isDisplayed()))
    }

    @Test fun firstName_isInitiallyEmpty() = editTextIsEmpty(R.id.first_name)

    @Test fun lastInitial_isInitiallyEmpty() = editTextIsEmpty(R.id.last_initial)

    @Test fun avatar_allDisplayed() = checkOnAvatar(isDisplayed())

    @Test fun avatar_isEnabled() = checkOnAvatar(ViewMatchers.isEnabled())

    @Test fun avatar_notFocusable() = checkOnAvatar(not(ViewMatchers.isFocusable()))

    @Test fun avatar_notClickable() = checkOnAvatar(not(ViewMatchers.isClickable()))

    @Test fun avatar_noneChecked() = checkOnAvatar(not(ViewMatchers.isChecked()))

    private fun checkOnAvatar(matcher: Matcher<View>) {
        (0 until Avatar.values().size).forEach {
            Espresso.onData(Matchers.equalTo(Avatar.values()[it]))
                    .inAdapterView(withId(R.id.avatars))
                    .check(matches(matcher))
        }
    }

    private fun onDoneView() = onView(withId(R.id.done))

    private fun inputData(firstName: String?, lastInitial: String?, avatar: Avatar?) {
        if (firstName != null) typeAndHideKeyboard(R.id.first_name, firstName)
        if (lastInitial != null) typeAndHideKeyboard(R.id.last_initial, lastInitial)
        if (avatar != null) clickAvatar(avatar)
    }

    private fun typeAndHideKeyboard(targetViewId: Int, text: String) {
        onView(withId(targetViewId)).perform(ViewActions.typeText(text), ViewActions.closeSoftKeyboard())
    }

    private fun clickAvatar(avatar: Avatar) {
        Espresso.onData(Matchers.equalTo(avatar))
                .inAdapterView(withId(R.id.avatars))
                .perform(ViewActions.click())
    }

    private fun editTextIsEmpty(id: Int) {
        onView(withId(id)).check(matches(ViewMatchers.withText(Matchers.isEmptyOrNullString())))
    }

}