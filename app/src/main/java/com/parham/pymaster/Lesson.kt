package com.parham.pymaster

data class Lesson(
    val id: Int,
    val title: String,
    val description: String,
    val explanation: String,
    val example: String,
    val exercise: String,
    val expectedOutput: String,
    val xp: Int = 10
)

object LessonLibrary {

    val lessons = listOf(

        Lesson(
            id = 1,
            title = "اولین برنامه پایتون",
            description = "آشنایی با دستور print",
            explanation = """
                دستور print برای نمایش اطلاعات در خروجی استفاده می‌شود.

                می‌توانیم متن، عدد و نتیجه محاسبات را نمایش دهیم.
            """.trimIndent(),
            example = "print('Hello World!')",
            exercise = "با print نام خودت را نمایش بده.",
            expectedOutput = "نام خودت",
            xp = 10
        ),

        Lesson(
            id = 2,
            title = "اعداد در پایتون",
            description = "محاسبات ساده",
            explanation = """
                پایتون می‌تواند محاسبات ریاضی انجام دهد.

                + جمع
                - تفریق
                * ضرب
                / تقسیم
            """.trimIndent(),
            example = "print(10 + 5)",
            exercise = "حاصل 20 + 30 را نمایش بده.",
            expectedOutput = "50",
            xp = 10
        ),

        Lesson(
            id = 3,
            title = "متغیرها",
            description = "ذخیره اطلاعات",
            explanation = """
                متغیر مانند یک جعبه برای نگهداری اطلاعات است.

                مثال:
                age = 13
                print(age)
            """.trimIndent(),
            example = "name = 'Parham'\nprint(name)",
            exercise = "یک متغیر به نام city بساز و نام شهرت را در آن ذخیره کن.",
            expectedOutput = "نام شهر",
            xp = 15
        )
    )
}