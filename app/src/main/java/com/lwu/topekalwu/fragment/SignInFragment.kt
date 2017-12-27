package com.lwu.topekalwu.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.GridView
import android.widget.Toast
import com.lwu.topekalwu.R
import com.lwu.topekalwu.adapter.AvatarAdapter
import com.lwu.topekalwu.helper.getPlayer
import com.lwu.topekalwu.helper.onLayoutChange
import com.lwu.topekalwu.model.Avatar
import com.lwu.topekalwu.model.Player
import com.lwu.topekalwu.widget.TextWatcherAdapter

/**
 * Created by lwu on 12/10/17.
 */
class SignInFragment : Fragment() {

    private var firstNameView: EditText? = null
    private var lastInitialView: EditText? = null
    private var doneFab: FloatingActionButton? = null
    private var avatarGrid: GridView? = null

    private var edit: Boolean = false

    private lateinit var player: Player
    private var selectedAvatarView: View? = null
    private var selectedAvatar: Avatar? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        player = activity.getPlayer()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            val avatarIndex = savedInstanceState.getInt(KEY_SELECTED_AVATAR_INDEX)
            if (avatarIndex != GridView.INVALID_POSITION) {
                selectedAvatar = Avatar.values()[avatarIndex]
            }
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val contentView = inflater.inflate(R.layout.fragment_sign_in, container, false)

        contentView.onLayoutChange {
            avatarGrid?.apply {
                adapter = AvatarAdapter(activity)
                onItemClickListener = AdapterView.OnItemClickListener {
                    _, view, position, _ ->
                    selectedAvatarView = view
                    selectedAvatar = Avatar.values()[position]
                    if (isInputDataValid()) doneFab?.show()
                }
                numColumns = calculateSpanCount()
                selectedAvatar?.let { setItemChecked(it.ordinal, true) }
            }
        }
        return contentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        firstNameView = view.findViewById<EditText>(R.id.first_name)
        lastInitialView = view.findViewById<EditText>(R.id.last_initial)
        doneFab = view.findViewById<FloatingActionButton>(R.id.done)
        avatarGrid = view.findViewById<GridView>(R.id.avatars)

        checkIsInEditMode()

        if (edit || !player.valid()) {
            view.findViewById<View>(R.id.empty).visibility = View.GONE
            view.findViewById<View>(R.id.content).visibility = View.VISIBLE
            initContentViews()
            initContents()
        } else {
            //TODO
            Toast.makeText(activity, "CategorySelectionActivity", Toast.LENGTH_LONG).show()
        }

        super.onViewCreated(view, savedInstanceState)

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putInt(KEY_SELECTED_AVATAR_INDEX, (avatarGrid?.checkedItemPosition ?: 0))
        super.onSaveInstanceState(outState)
    }

    private fun calculateSpanCount(): Int {
        val avatarSize = resources.getDimensionPixelSize(R.dimen.size_fab)
        val avatarPadding = resources.getDimensionPixelSize(R.dimen.spacing_double)
        return (avatarGrid?.width ?: 0) / (avatarSize + avatarPadding)
    }

    private fun isInputDataValid() =
        firstNameView?.text?.isNotEmpty() == true &&
                lastInitialView?.text?.isNotEmpty() == true

    private fun checkIsInEditMode() {
        if (arguments == null) {
            edit = false
        } else {
            edit = arguments.getBoolean(ARG_EDIT, false)
        }
    }

    private fun isAvatarSelected() = selectedAvatarView != null || selectedAvatar != null

    @SuppressLint("NewApi")
    private fun initContentViews() {
        val textWatcher = object : TextWatcher by TextWatcherAdapter {

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // hiding the floating action button if text is empty
                if (s.isEmpty()) {
                    doneFab?.hide()
                }
            }

            // showing the floating action button if avatar is selected and input data is valid
            override fun afterTextChanged(s: Editable) {
                if (isAvatarSelected() && isInputDataValid()) doneFab?.show()
            }
        }

        firstNameView?.addTextChangedListener(textWatcher)
        lastInitialView?.addTextChangedListener(textWatcher)
        doneFab?.setOnClickListener {

        }
    }

    private fun initContents() {
        with(player) {
            valid().let {
                firstNameView?.setText(firstName)
                lastInitialView?.setText(lastInitial)
                selectedAvatar = avatar
            }
        }
    }

    companion object {

        private const val ARG_EDIT = "EDIT"
        private const val KEY_SELECTED_AVATAR_INDEX = "selectedAvatarIndex"

        fun newInstance(edit: Boolean = false): SignInFragment {
            return SignInFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(ARG_EDIT, edit)
                }
            }
        }
    }
}