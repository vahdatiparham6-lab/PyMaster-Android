
package com.parham.pymaster

import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.*
import android.app.AlertDialog
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    private lateinit var stats: TextView
    private lateinit var output: TextView
    private lateinit var editor: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        PythonEngine.initialize(this)
        StreakManager.recordActivity(this)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24, 32, 24, 32)
            setBackgroundColor(Color.rgb(18, 24, 38))
        }

        val title = TextView(this).apply {
            text = "🐍 PyMaster"
            textSize = 32f
            gravity = Gravity.CENTER
            setTextColor(Color.WHITE)
        }

        stats = TextView(this).apply {
            textSize = 15f
            gravity = Gravity.CENTER
            setTextColor(Color.CYAN)
            setPadding(12, 24, 12, 24)
        }

        val lessonsButton = Button(this).apply {
            text = "📚 کتابخانه درس‌ها"
        }

        val historyButton = Button(this).apply {
            text = "🕘 تاریخچه کدها"
        }

        editor = EditText(this).apply {
            setText("print('Hello PyMaster!')")
            textSize = 16f
            setTextColor(Color.WHITE)
            setHintTextColor(Color.LTGRAY)
            setHint("کد پایتون خود را بنویس...")
            setBackgroundColor(Color.rgb(30, 41, 59))
            minLines = 5
            gravity = Gravity.TOP
        }

        val runButton = Button(this).apply {
            text = "▶ اجرای کد"
        }

        val saveButton = Button(this).apply {
            text = "💾 ذخیره کد"
        }

        output = TextView(this).apply {
            text = "خروجی برنامه اینجا نمایش داده می‌شود."
            textSize = 16f
            setTextColor(Color.GREEN)
            setPadding(12, 20, 12, 20)
        }

        lessonsButton.setOnClickListener {
            LessonScreen.show(this) {
                updateStats()
            }
        }

        runButton.setOnClickListener {
            val code = editor.text.toString()

            val error = ValidationManager.validatePythonCode(code)

            if (error != null) {
                output.text = error
                return@setOnClickListener
            }

            output.text = PythonEngine.runCode(
                CodeFormatter.format(code)
            )

            AchievementManager.unlock(this, "first_code")
        }

        saveButton.setOnClickListener {
            val code = editor.text.toString()

            if (code.isBlank()) {
                Toast.makeText(
                    this,
                    "اول یک کد بنویس!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                CodeHistory.saveCode(this, code)

                Toast.makeText(
                    this,
                    "کد ذخیره شد! 💾",
                    Toast.LENGTH_SHORT
                ).show()

                updateStats()
            }
        }

        historyButton.setOnClickListener {
            showCodeHistory()
        }

        layout.addView(title)
        layout.addView(stats)
        layout.addView(lessonsButton)
        layout.addView(historyButton)
        layout.addView(editor)
        layout.addView(runButton)
        layout.addView(saveButton)
        layout.addView(output)

        setContentView(
            ScrollView(this).apply {
                addView(layout)
            }
        )

        updateStats()
    }

    private fun showCodeHistory() {
        val history = CodeHistory.getHistory(this)

        if (history.isEmpty()) {
            AlertDialog.Builder(this)
                .setMessage("هنوز هیچ کدی ذخیره نکرده‌ای!")
                .setPositiveButton("باشه", null)
                .show()
            return
        }

        AlertDialog.Builder(this)
            .setTitle("🕘 تاریخچه کدها")
            .setItems(history.toTypedArray()) { _, index ->
                editor.setText(history[index])
            }
            .setNegativeButton("بستن", null)
            .show()
    }

    private fun updateStats() {
        stats.text = StatisticsManager.getSummary(this)
    }
}
