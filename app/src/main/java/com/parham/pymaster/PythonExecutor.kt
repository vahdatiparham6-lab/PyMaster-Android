
package com.parham.pymaster

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executors

object PythonExecutor {

    private val executor = Executors.newSingleThreadExecutor()
    private val mainHandler = Handler(Looper.getMainLooper())

    fun execute(
        code: String,
        onResult: (String) -> Unit
    ) {
        executor.execute {
            val result = try {
                PythonEngine.runCode(code)
            } catch (e: Exception) {
                "خطا در اجرای کد:\n${e.message}"
            }

            mainHandler.post {
                onResult(result)
            }
        }
    }

    fun shutdown() {
        executor.shutdown()
    }
}
