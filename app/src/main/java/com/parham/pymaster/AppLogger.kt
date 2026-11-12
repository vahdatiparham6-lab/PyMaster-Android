
package com.parham.pymaster

import android.content.Context
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object AppLogger {

    private const val FILE_NAME = "pymaster.log"
    private const val MAX_LOG_SIZE = 512 * 1024L

    @Synchronized
    fun log(
        context: Context,
        level: String,
        message: String
    ) {
        try {
            val directory = File(context.filesDir, "logs")

            if (!directory.exists()) {
                directory.mkdirs()
            }

            val file = File(directory, FILE_NAME)

            if (file.exists() && file.length() > MAX_LOG_SIZE) {
                file.writeText(
                    "Log rotated at ${System.currentTimeMillis()}\n"
                )
            }

            val timestamp = SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss",
                Locale.getDefault()
            ).format(Date())

            val safeMessage = message
                .replace("\n", " ")
                .take(1000)

            file.appendText(
                "[$timestamp] [$level] $safeMessage\n"
            )

        } catch (_: Exception) {
            // Logging must not crash the application.
        }
    }

    fun info(context: Context, message: String) {
        log(context, "INFO", message)
    }

    fun warning(context: Context, message: String) {
        log(context, "WARNING", message)
    }

    fun error(context: Context, message: String) {
        log(context, "ERROR", message)
    }

    fun readLogs(context: Context): String {
        return try {
            val file = File(
                File(context.filesDir, "logs"),
                FILE_NAME
            )

            if (file.exists()) {
                file.readText()
            } else {
                "هنوز گزارشی ثبت نشده است."
            }
        } catch (_: Exception) {
            "خواندن گزارش‌ها ناموفق بود."
        }
    }

    fun clearLogs(context: Context) {
        try {
            File(
                File(context.filesDir, "logs"),
                FILE_NAME
            ).delete()
        } catch (_: Exception) {
            // Ignore cleanup errors.
        }
    }
}
