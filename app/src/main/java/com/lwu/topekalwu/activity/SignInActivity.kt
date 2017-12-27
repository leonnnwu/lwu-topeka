package com.lwu.topekalwu.activity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import com.lwu.topekalwu.R
import com.lwu.topekalwu.fragment.SignInFragment
import com.lwu.topekalwu.helper.isSignedIn
import com.lwu.topekalwu.helper.replaceFragment

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        if (savedInstanceState == null) {
            replaceFragment(R.id.sign_in_container, SignInFragment.newInstance(isInEditMode))
        }
    }

    override fun onStop() {
        super.onStop()
        if (isSignedIn()) finish()
    }

    private val isInEditMode
        get() = intent.getBooleanExtra(EXTRA_EDIT, false)

    companion object {
        private const val EXTRA_EDIT = "EDIT"

        fun start(activity: Activity, edit: Boolean = false) {
            ActivityCompat.startActivity(activity,
                    Intent(activity, SignInActivity::class.java)
                            .apply { putExtra(EXTRA_EDIT, edit) },
                    ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle())
        }
    }
}
