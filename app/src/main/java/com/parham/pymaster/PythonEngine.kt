package com.parham.pymaster

import android.content.Context
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform

object PythonEngine {

    fun initialize(context: Context) {
        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(context))
        }
    }

    fun runCode(code: String): String {
        return try {
            val python = Python.getInstance()
            val module = python.getModule("runner")
            module.callAttr("run_code", code).toString()
        } catch (e: Exception) {
            "خطا در اجرای پایتون:\n${e.message}"
        }
    }
}