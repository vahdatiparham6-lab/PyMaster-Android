
package com.parham.pymaster

import android.graphics.Color

data class AppTheme(
    val background: Int,
    val surface: Int,
    val primary: Int,
    val text: Int,
    val secondaryText: Int,
    val success: Int
)

object ThemeManager {

    fun getTheme(darkMode: Boolean): AppTheme {

        return if (darkMode) {
            AppTheme(
                background = Color.rgb(18, 24, 38),
                surface = Color.rgb(30, 41, 59),
                primary = Color.rgb(56, 189, 248),
                text = Color.WHITE,
                secondaryText = Color.rgb(148, 163, 184),
                success = Color.rgb(34, 197, 94)
            )
        } else {
            AppTheme(
                background = Color.rgb(248, 250, 252),
                surface = Color.WHITE,
                primary = Color.rgb(2, 132, 199),
                text = Color.rgb(15, 23, 42),
                secondaryText = Color.rgb(100, 116, 139),
                success = Color.rgb(22, 163, 74)
            )
        }
    }
}
