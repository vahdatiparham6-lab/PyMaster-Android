
package com.parham.pymaster

object ValidationManager {

    fun validateUsername(username: String): String? {
        return when {
            username.isBlank() ->
                "نام کاربری نمی‌تواند خالی باشد."

            username.length < 3 ->
                "نام کاربری باید حداقل ۳ کاراکتر باشد."

            username.length > 24 ->
                "نام کاربری نباید بیشتر از ۲۴ کاراکتر باشد."

            else -> null
        }
    }

    fun validatePythonCode(code: String): String? {
        return when {
            code.isBlank() ->
                "کد پایتون خالی است."

            code.length > 10000 ->
                "کد بیش از حد طولانی است."

            else -> null
        }
    }

    fun validateGoal(goal: String): String? {
        return when {
            goal.isBlank() ->
                "هدف یادگیری را وارد کن."

            goal.length > 100 ->
                "هدف یادگیری بیش از حد طولانی است."

            else -> null
        }
    }

    fun isValidFontSize(size: Int): Boolean {
        return size in 12..28
    }
}
