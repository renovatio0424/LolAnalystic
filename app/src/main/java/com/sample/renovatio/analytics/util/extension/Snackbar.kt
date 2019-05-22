package com.sample.renovatio.analytics.util.extension

import android.app.Activity
import android.content.Context
import android.view.View
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception


fun Activity.showSnackbar(stringResId: Int) = snackBarInit(this, this.resources.getString(stringResId)).show()

fun Activity.showSnackbar(message: String) = snackBarInit(this, message).show()

fun snackBarInit(context: Context, message: String) =
    Snackbar.make(getRootView(context), message, Snackbar.LENGTH_LONG)

fun Snackbar.setOnClickListener(buttonResId: Int, onClickListener: View.OnClickListener) =
    this.setAction(
        buttonResId,
        onClickListener
    )

fun getRootView(context: Context): View {
    if (context !is Activity)
        Exception("Snack bar need Root View")

    return (context as Activity).window.decorView.rootView
}