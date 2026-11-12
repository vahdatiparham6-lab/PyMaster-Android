
package com.parham.pymaster

object SearchManager {

    fun searchLessons(query: String): List<Lesson> {
        if (query.isBlank()) {
            return emptyList()
        }

        val normalizedQuery = query.trim()

        return LessonLibrary.lessons.filter { lesson ->
            lesson.title.contains(
                normalizedQuery,
                ignoreCase = true
            ) ||
            lesson.description.contains(
                normalizedQuery,
                ignoreCase = true
            ) ||
            lesson.explanation.contains(
                normalizedQuery,
                ignoreCase = true
            )
        }
    }

    fun searchById(id: Int): Lesson? {
        return LessonLibrary.lessons.find {
            it.id == id
        }
    }
}
