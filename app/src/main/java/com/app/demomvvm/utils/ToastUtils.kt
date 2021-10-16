package com.app.demomvvm.utils

import android.content.Context
import android.view.Gravity
import android.widget.Toast

const val DURATION = Toast.LENGTH_SHORT
const val GRAVITY = Gravity.CENTER_VERTICAL or Gravity.BOTTOM

fun Context.showToast(
    message: String?,
    duration: Int = DURATION,
    gravity: Int = GRAVITY
) {
    message?.let {
        val toast = Toast.makeText(this, message, DURATION)
        toast.setGravity(gravity, 0, 300)
        toast.setMargin(0f, 0f)
        toast.duration = duration
        toast.show()
    }
}
