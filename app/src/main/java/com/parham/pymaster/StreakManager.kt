
package com.parham.pymaster

import android.content.Context
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Calendar

object StreakManager {

    private const val PREFS = "pymaster_streak"

    private const val LAST_DATE = "last_date"
    private const val CURRENT_STREAK = "current_streak"
    private const val BEST_STREAK = "best_streak"

    fun recordActivity(context: Context): Int {
        val prefs = getPrefs(context)

        val today = getToday()
        val lastDate = prefs.getString(LAST_DATE, "") ?: ""

        var streak = prefs.getInt(CURRENT_STREAK, 0)

        if (lastDate == today) {
            return streak
        }

        val yesterday = getYesterday()

        streak = if (lastDate == yesterday) {
            streak + 1
        } else {
            1
        }

        val best = maxOf(
            streak,
            prefs.getInt(BEST_STREAK, 0)
        )

        prefs.edit()
            .putString(LAST_DATE, today)
            .putInt(CURRENT_STREAK, streak)
            .putInt(BEST_STREAK, best)
            .apply()

        return streak
    }

    fun getCurrentStreak(context: Context): Int {
        return getPrefs(context).getInt(CURRENT_STREAK, 0)
    }

    fun getBestStreak(context: Context): Int {
        return getPrefs(context).getInt(BEST_STREAK, 0)
    }

    private fun getToday(): String {
        return SimpleDateFormat(
            "yyyy-MM-dd",
            Locale.getDefault()
        ).format(Date())
    }

    private fun getYesterday(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -1)

        return SimpleDateFormat(
            "yyyy-MM-dd",
            Locale.getDefault()
        ).format(calendar.time)
    }

    private fun getPrefs(context: Context) =
        context.getSharedPreferences(
            PREFS,
            Context.MODE_PRIVATE
        )
}
