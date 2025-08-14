package tui

import kotlinx.coroutines.*

fun main(): kotlin.Unit = runBlocking {
    Terminal.hideCursor()
    Terminal.clear()

    Terminal.printAt(1, 1, "Kotlin TUI Dashboard", color = 46)
    Terminal.printAt(3, 1, "CPU Usage:", color = 82)
    Terminal.printAt(4, 1, "RAM Usage:", color = 82)
    Terminal.printAt(6, 1, "Press Ctrl+C to quit", color = 220)

    val cpuJob = launch {
        var percent = 0
        while (isActive) {
            Terminal.printAt(3, 15, "${percent}%   ", color = 196)
            percent = (percent + 5) % 101
            delay(500)
        }
    }

    val ramJob = launch {
        var percent = 50
        while (isActive) {
            Terminal.printAt(4, 15, "$percent%", color = 208)
            percent = (percent + 3) % 101
            delay(800)
        }
    }

    joinAll(cpuJob, ramJob)
}