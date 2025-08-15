import kotlinx.coroutines.*
import jline.TerminalFactory
import tui.Terminal

fun main(): Unit = runBlocking {

    Terminal.hideCursor()
    Terminal.clear()
    val terminal = TerminalFactory.get()
    Terminal.printAt(1, 0, "Width: 0, Height: 0", color = 208)
    Terminal.moveCursor(2,0)

    val resizeJob = launch {
        while (isActive) {
            val w = terminal.width
            val h = terminal.height
            Terminal.printAt(1, 0, "Width: $w, Height: $h", color = 208)
            delay(200)
        }
    }
    Terminal.testPage()

    Runtime.getRuntime().addShutdownHook(Thread {
        runBlocking {
            resizeJob.cancelAndJoin()
        }
        Terminal.showCursor()
        Terminal.clear()
        System.out.flush()
        println("All Cleared :D")
    })

    joinAll(resizeJob)
}
