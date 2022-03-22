package com.tung.musicapplication.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun closeKeyboard(activity: Activity) = with(activity) {
    currentFocus?.let { view ->
        val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun openKeyboard(activity: Activity, viewToFocus: View) = with(activity) {
    val imm: InputMethodManager =
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(viewToFocus, InputMethodManager.SHOW_IMPLICIT)
}