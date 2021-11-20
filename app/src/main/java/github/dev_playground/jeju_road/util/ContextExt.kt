package github.dev_playground.jeju_road.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.annotation.StringRes
import java.nio.charset.Charset

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

/**
 * load asset
 */
fun Context.loadAsset(fileName: String): String {
    return this.assets.open(fileName).use {
        val size = it.available()
        val buffer = ByteArray(size)
        it.read(buffer)

        String(buffer, Charset.forName("UTF-8"))
    }
}