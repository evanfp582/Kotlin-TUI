import kotlinx.coroutines.*
import jline.TerminalFactory
import tui.Terminal
import tui.UIManager
import tui.components.TextBox

fun main(): Unit = runBlocking {

    Terminal.hideCursor()
    Terminal.clear()
    val terminal = TerminalFactory.get()
    Terminal.printAt(1,0, "Welcome to the Kotlin Terminal User Interface!")
    Terminal.printAt(2, 0, "Width: 0, Height: 0", color = 208)
    Terminal.moveCursor(3,0)

    val resizeJob = launch {
        while (isActive) {
            val w = terminal.width
            val h = terminal.height
            Terminal.printAt(2, 0, "Width: $w, Height: $h", color = 208)
            delay(200)
        }
    }

    Terminal.testPage()

//    val ui = UIManager()
//    ui.addComponent(TextBox(3,1, 20, "Type Here"))
//    ui.run {
//        while (true) {
//            delay(500)
//        }
//    }

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
