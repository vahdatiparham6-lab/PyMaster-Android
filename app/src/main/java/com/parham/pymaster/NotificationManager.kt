
package com.parham.pymaster

import android.content.Context

data class AppNotification(
    val id: Int,
    val title: String,
    val message: String,
    val timestamp: Long
)

object AppNotificationManager {

    private const val PREFS = "pymaster_notifications"
    private const val NOTIFICATIONS = "notifications"

    fun save(
        context: Context,
        title: String,
        message: String
    ) {
        val prefs = context.getSharedPreferences(
            PREFS,
            Context.MODE_PRIVATE
        )

        val notifications = getAll(context).toMutableList()

        notifications.add(
            0,
            AppNotification(
                id = System.currentTimeMillis().toInt(),
                title = title,
                message = message,
                timestamp = System.currentTimeMillis()
            )
        )

        val saved = notifications.take(50).map {
            "${it.id}|${it.timestamp}|${it.title.replace("|", " ")}|${it.message.replace("|", " ")}"
        }.toSet()

        prefs.edit()
            .putStringSet(NOTIFICATIONS, saved)
            .apply()
    }

    fun getAll(context: Context): List<AppNotification> {
        val saved = context.getSharedPreferences(
            PREFS,
            Context.MODE_PRIVATE
        ).getStringSet(NOTIFICATIONS, emptySet())
            ?: emptySet()

        return saved.mapNotNull { item ->
            val parts = item.split("|", limit = 4)

            if (parts.size != 4) {
                null
            } else {
                AppNotification(
                    id = parts[0].toIntOrNull() ?: return@mapNotNull null,
                    timestamp = parts[1].toLongOrNull() ?: return@mapNotNull null,
                    title = parts[2],
                    message = parts[3]
                )
            }
        }.sortedByDescending { it.timestamp }
    }

    fun clear(context: Context) {
        context.getSharedPreferences(
            PREFS,
            Context.MODE_PRIVATE
        ).edit()
            .remove(NOTIFICATIONS)
            .apply()
    }
}
