
package com.parham.pymaster

data class CodeIssue(
    val line: Int,
    val message: String,
    val suggestion: String
)

object PythonCodeAnalyzer {

    fun analyze(code: String): List<CodeIssue> {
        val issues = mutableListOf<CodeIssue>()
        val lines = code.lines()

        lines.forEachIndexed { index, rawLine ->
            val line = rawLine.trim()

            if (line.isEmpty()) return@forEachIndexed

            if (line == "pritn") {
                issues.add(
                    CodeIssue(
                        index + 1,
                        "احتمالاً print را اشتباه نوشته‌ای.",
                        "از print() استفاده کن."
                    )
                )
            }

            if (
                line.startsWith("print ") &&
                !line.startsWith("print(")
            ) {
                issues.add(
                    CodeIssue(
                        index + 1,
                        "ساختار دستور print ممکن است اشتباه باشد.",
                        "مثلاً print('Hello')"
                    )
                )
            }

            if (
                line.startsWith("if ") &&
                !line.endsWith(":")
            ) {
                issues.add(
                    CodeIssue(
                        index + 1,
                        "دستور if احتمالاً به دونقطه نیاز دارد.",
                        "مثلاً if age > 10:"
                    )
                )
            }
        }

        return issues
    }

    fun getReport(code: String): String {
        val issues = analyze(code)

        if (issues.isEmpty()) {
            return "اشتباه ساده‌ای پیدا نشد! 🎉"
        }

        return issues.joinToString("\n\n") { issue ->
            """
            خط ${issue.line}
            ⚠️ ${issue.message}
            💡 ${issue.suggestion}
            """.trimIndent()
        }
    }
}
