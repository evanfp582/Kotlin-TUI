package tui

import kotlinx.coroutines.*
import jline.TerminalFactory

fun main(): Unit = runBlocking {
    // Terminal setup
    Terminal.hideCursor()
    Terminal.clear()
    val terminal = TerminalFactory.get()

    // Initial static display
    Terminal.printAt(1, 1, "Kotlin TUI Dashboard", color = 46)
    Terminal.printAt(3, 1, "CPU Usage:", color = 82)
    Terminal.printAt(4, 1, "RAM Usage:", color = 82)
    Terminal.printAt(9, 1, "Press Ctrl+C to quit", color = 220)

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

    val resizeJob = launch {
        while (isActive) {
            val w = terminal.width
            val h = terminal.height
            Terminal.printAt(8, 1, "Width: $w, Height: $h", color = 208)
            delay(200)
        }
    }

    Runtime.getRuntime().addShutdownHook(Thread {
        runBlocking {
            cpuJob.cancelAndJoin()
            ramJob.cancelAndJoin()
            resizeJob.cancelAndJoin()
        }
        Terminal.showCursor()
        Terminal.clear()
        System.out.flush()
        println("All Cleared :D")
    })

    joinAll(cpuJob, ramJob, resizeJob)
}
