
package com.parham.pymaster

import android.content.Context
import org.json.JSONArray

object CodeHistory {

    private const val PREFS = "pymaster_code_history"
    private const val HISTORY = "saved_codes"
    private const val MAX_HISTORY = 30

    fun saveCode(context: Context, code: String) {
        if (code.isBlank()) return

        val prefs = getPrefs(context)
        val history = getHistory(context).toMutableList()

        history.remove(code)
        history.add(0, code)

        val json = JSONArray()

        history.take(MAX_HISTORY).forEach {
            json.put(it)
        }

        prefs.edit()
            .putString(HISTORY, json.toString())
            .apply()
    }

    fun getHistory(context: Context): List<String> {
        val saved = getPrefs(context)
            .getString(HISTORY, null)
            ?: return emptyList()

        return try {
            val json = JSONArray(saved)
            List(json.length()) { index ->
                json.getString(index)
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun clearHistory(context: Context) {
        getPrefs(context).edit()
            .remove(HISTORY)
            .apply()
    }

    private fun getPrefs(context: Context) =
        context.getSharedPreferences(
            PREFS,
            Context.MODE_PRIVATE
        )
}
