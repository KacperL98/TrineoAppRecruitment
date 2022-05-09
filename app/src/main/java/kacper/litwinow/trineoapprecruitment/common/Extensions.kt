package kacper.litwinow.trineoapprecruitment.common

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kacper.litwinow.trineoapprecruitment.login.activity.LoginActivity

fun View.gone() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun Context.restartApplication() {
    val intent = Intent(this, LoginActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
    }
    startActivity(intent)
}

fun Fragment.hideKeyboard() {
    val inputMethodManager =
        requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = requireActivity().currentFocus
    if (view == null) {
        view = View(requireContext())
    }
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.toastMessage(message: Int) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()