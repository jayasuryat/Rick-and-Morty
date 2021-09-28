package com.jayasuryat.base

import android.view.View

public fun View.show() {
    if (visibility != View.VISIBLE) visibility = View.VISIBLE
}

public fun View.hide() {
    if (visibility == View.VISIBLE) visibility = View.GONE
}

public fun View.toggleVisibility() {
    when (visibility) {
        View.VISIBLE -> hide()
        View.GONE, View.INVISIBLE -> show()
    }
}

public fun View.toggleVisibility(shouldShow: Boolean) {
    if (shouldShow) show() else hide()
}
