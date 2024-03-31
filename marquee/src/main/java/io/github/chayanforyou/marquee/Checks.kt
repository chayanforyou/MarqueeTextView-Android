package io.github.chayanforyou.marquee

import android.content.Context
import android.os.Build
import java.util.Locale

internal object Checks {

    private fun getOrientation(locale: Locale): Orientation {
        // A more flexible implementation would consult a ResourceBundle
        // to find the appropriate orientation.  Until pluggable locales
        // are introduced however, the flexibility isn't really needed.
        // So we choose efficiency instead.
        val lang = locale.language
        return if ("iw" == lang || "ar" == lang || "fa" == lang || "ur" == lang) {
            Orientation.RIGHT_TO_LEFT
        } else {
            Orientation.LEFT_TO_RIGHT
        }
    }

    private fun getPrimaryLocale(context: Context): Locale {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.resources.configuration.getLocales()[0]
        } else {
            context.resources.configuration.locale
        }
    }

    fun isRtl(context: Context): Boolean {
        return getOrientation(getPrimaryLocale(context)) == Orientation.RIGHT_TO_LEFT
    }
}

internal enum class Orientation {
    RIGHT_TO_LEFT,
    LEFT_TO_RIGHT
}