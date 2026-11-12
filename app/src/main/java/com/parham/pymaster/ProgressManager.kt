package com.parham.pymaster

import android.content.Context

object ProgressManager {

    private const val PREFS = "pymaster_progress"

    fun getXP(context: Context): Int {
        return context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .getInt("xp", 0)
    }

    fun addXP(context: Context, amount: Int) {
        val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        val newXP = prefs.getInt("xp", 0) + amount
        prefs.edit().putInt("xp", newXP).apply()
    }

    fun getCompletedLessons(context: Context): Int {
        return context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .getInt("completed_lessons", 0)
    }

    fun completeLesson(context: Context) {
        val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        val count = prefs.getInt("completed_lessons", 0)
        prefs.edit().putInt("completed_lessons", count + 1).apply()
    }
}