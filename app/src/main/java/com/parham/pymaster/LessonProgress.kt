package com.parham.pymaster

import android.content.Context

object LessonProgress {

    private const val PREFS = "pymaster_lessons"
    private const val COMPLETED = "completed_ids"

    fun isCompleted(context: Context, lessonId: Int): Boolean {
        val prefs = context.getSharedPreferences(
            PREFS,
            Context.MODE_PRIVATE
        )

        return prefs.getStringSet(COMPLETED, emptySet())
            ?.contains(lessonId.toString()) == true
    }

    fun completeLesson(
        context: Context,
        lesson: Lesson
    ): Boolean {

        if (isCompleted(context, lesson.id)) {
            return false
        }

        val prefs = context.getSharedPreferences(
            PREFS,
            Context.MODE_PRIVATE
        )

        val completed = prefs
            .getStringSet(COMPLETED, emptySet())
            ?.toMutableSet() ?: mutableSetOf()

        completed.add(lesson.id.toString())

        prefs.edit()
            .putStringSet(COMPLETED, completed)
            .apply()

        ProgressManager.completeLesson(context)
        ProgressManager.addXP(context, lesson.xp)

        return true
    }
}