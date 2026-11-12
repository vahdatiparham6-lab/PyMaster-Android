
package com.parham.pymaster

enum class ExecutionStatus {
    IDLE,
    RUNNING,
    SUCCESS,
    ERROR
}

data class ExecutionState(
    val status: ExecutionStatus = ExecutionStatus.IDLE,
    val output: String = "",
    val message: String = ""
)

object ExecutionStateManager {

    fun idle(): ExecutionState {
        return ExecutionState(
            status = ExecutionStatus.IDLE,
            message = "آماده اجرای کد"
        )
    }

    fun running(): ExecutionState {
        return ExecutionState(
            status = ExecutionStatus.RUNNING,
            message = "در حال اجرای کد..."
        )
    }

    fun success(output: String): ExecutionState {
        return ExecutionState(
            status = ExecutionStatus.SUCCESS,
            output = output,
            message = "اجرای برنامه تمام شد."
        )
    }

    fun error(message: String): ExecutionState {
        return ExecutionState(
            status = ExecutionStatus.ERROR,
            message = message
        )
    }
}
