package com.parham.pymaster

import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.EditText
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        PythonEngine.initialize(this)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(32, 32, 32, 32)
            setBackgroundColor(Color.rgb(18, 24, 38))
        }

        val title = TextView(this).apply {
            text = "🐍 PyMaster"
            textSize = 32f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }

        val editor = EditText(this).apply {
            setText("print('Hello PyMaster!')")
            textSize = 16f
            setTextColor(Color.WHITE)
            setHintTextColor(Color.GRAY)
            setBackgroundColor(Color.rgb(30, 41, 59))
            minLines = 5
            gravity = Gravity.TOP
        }

        val runButton = Button(this).apply {
            text = "▶ اجرای کد"
        }

        val output = TextView(this).apply {
            text = "خروجی برنامه اینجا نمایش داده می‌شود."
            textSize = 16f
            setTextColor(Color.GREEN)
            setPadding(12, 20, 12, 20)
        }

        runButton.setOnClickListener {
            val code = editor.text.toString()
            output.text = PythonEngine.runCode(code)
        }

        layout.addView(title)
        layout.addView(editor)
        layout.addView(runButton)
        layout.addView(output)

        setContentView(layout)
    }
}