package tui

import java.io.File
import java.time.LocalDateTime

object DebugLogger {
    private val logFile = File("debug.log")

    fun initLog() {
        logFile.writeText("---Program Started ${LocalDateTime.now()}---\n")
    }

    fun log(message: String) {
        logFile.appendText("[${LocalDateTime.now()}]: $message\n")
    }
}