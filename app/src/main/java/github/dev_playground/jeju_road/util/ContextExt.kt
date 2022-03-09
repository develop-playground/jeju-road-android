package github.dev_playground.jeju_road.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.annotation.StringRes

/**
 * toast
 */

fun Context.showShort(@StringRes stringRes: Int) {
    showShort(getString(stringRes))
}

fun Context.showShort(charSequence: CharSequence) {
    showShort(charSequence.toString())
}

fun Context.showShort(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
}

fun Context.showLong(@StringRes stringRes: Int) {
    showLong(getString(stringRes))
}

fun Context.showLong(charSequence: CharSequence) {
    showLong(charSequence.toString())
}

fun Context.showLong(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_LONG).show()
}

/**
 * start activity
 */

inline fun <reified T : Activity> Context.startActivity(noinline action: Intent.() -> Unit) {
    startActivity(Intent(this, T::class.java).apply(action))
}