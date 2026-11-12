
package com.parham.pymaster

import android.content.Context

data class UserProfile(
    val username: String,
    val level: String,
    val learningGoal: String,
    val startDate: Long
)

object UserProfileManager {

    private const val PREFS = "pymaster_profile"

    fun getProfile(context: Context): UserProfile {
        val prefs = getPrefs(context)

        return UserProfile(
            username = prefs.getString("username", "Python Learner") ?: "Python Learner",
            level = prefs.getString("level", "Beginner") ?: "Beginner",
            learningGoal = prefs.getString("goal", "یادگیری پایتون") ?: "یادگیری پایتون",
            startDate = prefs.getLong("start_date", System.currentTimeMillis())
        )
    }

    fun saveProfile(
        context: Context,
        profile: UserProfile
    ) {
        getPrefs(context).edit()
            .putString("username", profile.username)
            .putString("level", profile.level)
            .putString("goal", profile.learningGoal)
            .putLong("start_date", profile.startDate)
            .apply()
    }

    fun updateUsername(
        context: Context,
        username: String
    ) {
        getPrefs(context).edit()
            .putString("username", username.trim())
            .apply()
    }

    fun updateGoal(
        context: Context,
        goal: String
    ) {
        getPrefs(context).edit()
            .putString("goal", goal.trim())
            .apply()
    }

    private fun getPrefs(context: Context) =
        context.getSharedPreferences(
            PREFS,
            Context.MODE_PRIVATE
        )
}
