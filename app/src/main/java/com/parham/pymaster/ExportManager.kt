
package com.parham.pymaster

import android.content.Context
import java.io.File

object ExportManager {

    fun exportCode(
        context: Context,
        fileName: String,
        code: String
    ): File? {

        return try {
            val safeName = fileName
                .replace(Regex("[^a-zA-Z0-9_-]"), "_")
                .take(40)
                .ifBlank { "my_code" }

            val directory = File(
                context.getExternalFilesDir(null),
                "PyMaster"
            )

            if (!directory.exists()) {
                directory.mkdirs()
            }

            val file = File(
                directory,
                "$safeName.py"
            )

            file.writeText(code, Charsets.UTF_8)

            file
        } catch (e: Exception) {
            null
        }
    }

    fun getExportDirectory(context: Context): File? {
        return context.getExternalFilesDir("PyMaster")
    }
}
