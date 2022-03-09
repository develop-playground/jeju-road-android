package github.dev_playground.jeju_road.data.util

import android.content.Context
import java.nio.charset.Charset

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