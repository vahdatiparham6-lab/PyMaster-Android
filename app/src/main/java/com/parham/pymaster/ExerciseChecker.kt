
package com.parham.pymaster

object ExerciseChecker {

    fun check(
        actualOutput: String,
        expectedOutput: String
    ): Boolean {

        val actual = normalize(actualOutput)
        val expected = normalize(expectedOutput)

        return actual == expected
    }

    private fun normalize(text: String): String {
        return text
            .trim()
            .replace("\r\n", "\n")
            .replace("\r", "\n")
    }

    fun getMessage(
        actualOutput: String,
        expectedOutput: String
    ): String {

        return if (check(actualOutput, expectedOutput)) {
            "آفرین! پاسخ درست است! 🎉"
        } else {
            "هنوز درست نیست! دوباره امتحان کن. 💪"
        }
    }
}
