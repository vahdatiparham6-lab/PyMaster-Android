package com.parham.pymaster

import android.content.Context
import androidx.appcompat.app.AlertDialog

object LessonScreen {

    fun show(
        context: Context,
        onProgressChanged: () -> Unit
    ) {
        val lessons = LessonLibrary.lessons

        val titles = lessons.map { lesson ->
            val completed = LessonProgress.isCompleted(
                context,
                lesson.id
            )

            "${if (completed) "✅" else "📘"} ${lesson.title}"
        }

        AlertDialog.Builder(context)
            .setTitle("📚 کتابخانه درس‌های PyMaster")
            .setItems(titles.toTypedArray()) { _, index ->
                showLesson(
                    context,
                    lessons[index],
                    onProgressChanged
                )
            }
            .setNegativeButton("بستن", null)
            .show()
    }

    private fun showLesson(
        context: Context,
        lesson: Lesson,
        onProgressChanged: () -> Unit
    ) {
        val content = """
            ${lesson.description}

            ${lesson.explanation}

            🧪 مثال:

            ${lesson.example}

            ✏️ تمرین:

            ${lesson.exercise}
        """.trimIndent()

        AlertDialog.Builder(context)
            .setTitle(lesson.title)
            .setMessage(content)
            .setPositiveButton("تکمیل درس ⭐") { _, _ ->

                val completed = LessonProgress.completeLesson(
                    context,
                    lesson
                )

                if (completed) {
                    onProgressChanged()
                    AlertDialog.Builder(context)
                        .setMessage("آفرین! ${lesson.xp} XP گرفتی 🎉")
                        .setPositiveButton("عالی!", null)
                        .show()
                } else {
                    AlertDialog.Builder(context)
                        .setMessage("این درس قبلاً تکمیل شده است!")
                        .setPositiveButton("باشه", null)
                        .show()
                }
            }
            .setNegativeButton("بازگشت", null)
            .show()
    }
}