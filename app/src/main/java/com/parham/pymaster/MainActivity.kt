package com.parham.pymaster

import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.*
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    private lateinit var stats: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        PythonEngine.initialize(this)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24, 32, 24, 32)
            setBackgroundColor(Color.rgb(18, 24, 38))
        }

        val title = TextView(this).apply {
            text = "🐍 PyMaster"
            textSize = 32f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }

        stats = TextView(this).apply {
            textSize = 18f
            setTextColor(Color.CYAN)
            gravity = Gravity.CENTER
            setPadding(12, 24, 12, 24)
        }

        val editor = EditText(this).apply {
            setText("print('Hello PyMaster!')")
            textSize = 16f
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.rgb(30, 41, 59))
            minLines = 5
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

        val lessonButton = Button(this).apply {
            text = "⭐ تکمیل یک درس آزمایشی"
        }

        runButton.setOnClickListener {
            output.text = PythonEngine.runCode(
                editor.text.toString()
            )
        }

        lessonButton.setOnClickListener {
            ProgressManager.completeLesson(this)
            ProgressManager.addXP(this, 10)
            updateStats()
        }

        layout.addView(title)
        layout.addView(stats)
        layout.addView(editor)
        layout.addView(runButton)
        layout.addView(output)
        layout.addView(lessonButton)

        setContentView(
            ScrollView(this).apply {
                addView(layout)
            }
        )

        updateStats()
    }

    private fun updateStats() {
        val xp = ProgressManager.getXP(this)
        val lessons = ProgressManager.getCompletedLessons(this)

        stats.text = "⭐ XP: $xp\n📚 درس‌های تکمیل‌شده: $lessons"
    }
}