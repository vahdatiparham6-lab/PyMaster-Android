
package com.parham.pymaster

import android.content.Context

data class LearningStatistics(
    val totalXP: Int,
    val completedLessons: Int,
    val savedCodes: Int,
    val currentStreak: Int,
    val bestStreak: Int,
    val unlockedAchievements: Int
)

object StatisticsManager {

    fun getStatistics(context: Context): LearningStatistics {

        return LearningStatistics(
            totalXP = ProgressManager.getXP(context),

            completedLessons =
                ProgressManager.getCompletedLessons(context),

            savedCodes =
                CodeHistory.getHistory(context).size,

            currentStreak =
                StreakManager.getCurrentStreak(context),

            bestStreak =
                StreakManager.getBestStreak(context),

            unlockedAchievements =
                AchievementManager.getUnlocked(context).size
        )
    }

    fun getSummary(context: Context): String {
        val stats = getStatistics(context)

        return """
            ⭐ مجموع XP: ${stats.totalXP}

            📚 درس‌های تکمیل‌شده: ${stats.completedLessons}

            💻 کدهای ذخیره‌شده: ${stats.savedCodes}

            🔥 روزهای متوالی: ${stats.currentStreak}

            🏆 بهترین رکورد: ${stats.bestStreak}

            🎖 دستاوردها: ${stats.unlockedAchievements}
        """.trimIndent()
    }
}
