package tui

import jline.TerminalFactory
import jline.console.ConsoleReader
import kotlinx.coroutines.*


class UIManager() {
    private val screens = mutableListOf<ScreenObject>()
    private var currentScreen: ScreenObject? = null
    //TODO Add a screen stack that keeps track of the order of screens traversed

    fun addScreen(screen: ScreenObject) {
        screens.add(screen)
        if (currentScreen == null) {
         currentScreen = screen
        }
    }

    suspend fun run() {
        Terminal.hideCursor()
        Terminal.clear()

        val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())

        val renderJob = scope.launch {
            while (isActive) {
                currentScreen?.renderAll()
                currentScreen?.render()
                delay(10)
            }
        }

        val inputJob = scope.launch {
            val terminal = TerminalFactory.get()
            val reader = ConsoleReader()
            terminal.isEchoEnabled = false
            reader.bellEnabled = false

            while (isActive) {
                when (val ch = reader.readCharacter()) {
                    -1 -> cancel()            // EOF
                    'q'.code -> cancel()      // quit
                    else -> {
                        currentScreen?.components?.forEach { it.handleInput(ch.toChar()) }
                    }
                }
            }
        }

        inputJob.join()
        renderJob.cancelAndJoin()

        Terminal.showCursor()
        Terminal.clear()
    }
}