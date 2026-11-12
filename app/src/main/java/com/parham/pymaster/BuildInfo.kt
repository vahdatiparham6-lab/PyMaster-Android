
package com.parham.pymaster

object BuildInfo {

    const val APP_NAME = "PyMaster"

    const val VERSION_NAME = "0.1.0"

    const val VERSION_CODE = 1

    const val BUILD_TYPE = "debug"

    const val PYTHON_VERSION = "3.13"

    const val MINIMUM_ANDROID_VERSION = 26

    const val BUILD_STATUS = "Development"

    fun getBuildSummary(): String {
        return """
            App: $APP_NAME
            Version: $VERSION_NAME
            Version Code: $VERSION_CODE
            Build Type: $BUILD_TYPE
            Python: $PYTHON_VERSION
            Minimum Android SDK: $MINIMUM_ANDROID_VERSION
            Status: $BUILD_STATUS
        """.trimIndent()
    }
}
