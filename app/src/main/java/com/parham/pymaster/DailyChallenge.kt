
package com.parham.pymaster

import android.content.Context
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Challenge(
    val id: Int,
    val title: String,
    val description: String,
    val example: String,
    val xp: Int
)

object DailyChallengeManager {

    private const val PREFS = "pymaster_daily"
    private const val COMPLETED_DATE = "completed_date"

    private val challenges = listOf(
        Challenge(
            1,
            "اولین خروجی",
            "با print عبارت Hello Python را نمایش بده.",
            "print('Hello Python')",
            10
        ),
        Challenge(
            2,
            "ماشین حساب",
            "حاصل جمع 25 و 15 را نمایش بده.",
            "print(25 + 15)",
            15
        ),
        Challenge(
            3,
            "متغیرها",
            "یک متغیر به نام age بساز.",
            "age = 13\nprint(age)",
            15
        ),
        Challenge(
            4,
            "متن و رشته",
            "نام خودت را در یک متغیر ذخیره کن.",
            "name = 'Python'\nprint(name)",
            20
        )
    )

    fun getTodayChallenge(): Challenge {
        val day = getDayNumber()
        return challenges[day % challenges.size]
    }

    fun isCompletedToday(context: Context): Boolean {
        val savedDate = getPrefs(context)
            .getString(COMPLETED_DATE, "")

        return savedDate == getTodayDate()
    }

    fun completeToday(context: Context): Boolean {
        if (isCompletedToday(context)) {
            return false
        }

        getPrefs(context).edit()
            .putString(COMPLETED_DATE, getTodayDate())
            .apply()

        ProgressManager.addXP(
            context,
            getTodayChallenge().xp
        )

        return true
    }

    private fun getDayNumber(): Int {
        return (System.currentTimeMillis() / 86_400_000L).toInt()
    }

    private fun getTodayDate(): String {
        return SimpleDateFormat(
            "yyyy-MM-dd",
            Locale.getDefault()
        ).format(Date())
    }

    private fun getPrefs(context: Context) =
        context.getSharedPreferences(
            PREFS,
            Context.MODE_PRIVATE
        )
}
