
package com.parham.pymaster

import android.content.Context

data class Achievement(
    val id: String,
    val title: String,
    val description: String,
    val icon: String
)

object AchievementManager {

    private const val PREFS = "pymaster_achievements"
    private const val UNLOCKED = "unlocked"

    val achievements = listOf(
        Achievement(
            "first_lesson",
            "اولین قدم",
            "اولین درس خود را کامل کردی!",
            "🎓"
        ),
        Achievement(
            "five_lessons",
            "شاگرد پرتلاش",
            "پنج درس را کامل کردی!",
            "📚"
        ),
        Achievement(
            "first_code",
            "اولین کد",
            "اولین برنامه پایتونت را اجرا کردی!",
            "💻"
        ),
        Achievement(
            "ten_codes",
            "برنامه‌نویس تازه‌کار",
            "ده برنامه اجرا کردی!",
            "🚀"
        )
    )

    fun unlock(
        context: Context,
        achievementId: String
    ): Boolean {

        if (achievements.none { it.id == achievementId }) {
            return false
        }

        val prefs = context.getSharedPreferences(
            PREFS,
            Context.MODE_PRIVATE
        )

        val unlocked = prefs.getStringSet(
            UNLOCKED,
            emptySet()
        )?.toMutableSet() ?: mutableSetOf()

        if (!unlocked.add(achievementId)) {
            return false
        }

        prefs.edit()
            .putStringSet(UNLOCKED, unlocked)
            .apply()

        return true
    }

    fun isUnlocked(
        context: Context,
        achievementId: String
    ): Boolean {

        val unlocked = context.getSharedPreferences(
            PREFS,
            Context.MODE_PRIVATE
        ).getStringSet(UNLOCKED, emptySet())

        return unlocked?.contains(achievementId) == true
    }

    fun getUnlocked(
        context: Context
    ): List<Achievement> {

        return achievements.filter {
            isUnlocked(context, it.id)
        }
    }
}
