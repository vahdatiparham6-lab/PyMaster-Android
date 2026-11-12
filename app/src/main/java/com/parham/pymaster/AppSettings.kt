
package com.parham.pymaster

import android.content.Context

object AppSettings {

    private const val PREFS = "pymaster_settings"

    private const val DARK_MODE = "dark_mode"
    private const val SOUND_ENABLED = "sound_enabled"
    private const val FONT_SIZE = "font_size"

    fun isDarkMode(context: Context): Boolean {
        return getPrefs(context).getBoolean(
            DARK_MODE,
            true
        )
    }

    fun setDarkMode(
        context: Context,
        enabled: Boolean
    ) {
        getPrefs(context).edit()
            .putBoolean(DARK_MODE, enabled)
            .apply()
    }

    fun isSoundEnabled(context: Context): Boolean {
        return getPrefs(context).getBoolean(
            SOUND_ENABLED,
            true
        )
    }

    fun setSoundEnabled(
        context: Context,
        enabled: Boolean
    ) {
        getPrefs(context).edit()
            .putBoolean(SOUND_ENABLED, enabled)
            .apply()
    }

    fun getFontSize(context: Context): Int {
        return getPrefs(context).getInt(
            FONT_SIZE,
            16
        )
    }

    fun setFontSize(
        context: Context,
        size: Int
    ) {
        getPrefs(context).edit()
            .putInt(FONT_SIZE, size.coerceIn(12, 28))
            .apply()
    }

    private fun getPrefs(context: Context) =
        context.getSharedPreferences(
            PREFS,
            Context.MODE_PRIVATE
        )
}
