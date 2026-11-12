
package com.parham.pymaster

import android.content.Context

object CodeHistory {

    private const val PREFS = "pymaster_code_history"
    private const val HISTORY = "saved_codes"
    private const val MAX_HISTORY = 30

    fun saveCode(context: Context, code: String) {
        if (code.isBlank()) return

        val prefs = context.getSharedPreferences(
            PREFS,
            Context.MODE_PRIVATE
        )

        val history = prefs.getStringSet(
            HISTORY,
            emptySet()
        )?.toMutableList() ?: mutableListOf()

        history.remove(code)
        history.add(0, code)

        val limitedHistory = history.take(MAX_HISTORY).toSet()

        prefs.edit()
            .putStringSet(HISTORY, limitedHistory)
            .apply()
    }

    fun getHistory(context: Context): List<String> {
        val prefs = context.getSharedPreferences(
            PREFS,
            Context.MODE_PRIVATE
        )

        return prefs.getStringSet(
            HISTORY,
            emptySet()
        )?.toList() ?: emptyList()
    }

    fun clearHistory(context: Context) {
        context.getSharedPreferences(
            PREFS,
            Context.MODE_PRIVATE
        ).edit()
            .remove(HISTORY)
            .apply()
    }
}
