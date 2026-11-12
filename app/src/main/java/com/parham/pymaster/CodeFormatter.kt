
package com.parham.pymaster

object CodeFormatter {

    fun format(code: String): String {
        return code
            .replace("\r\n", "\n")
            .replace("\r", "\n")
            .lines()
            .joinToString("\n") { line ->
                line.trimEnd()
            }
            .trim()
    }

    fun countLines(code: String): Int {
        if (code.isBlank()) return 0
        return code.lines().size
    }

    fun countCharacters(code: String): Int {
        return code.length
    }

    fun isEmpty(code: String): Boolean {
        return code.isBlank()
    }
}
