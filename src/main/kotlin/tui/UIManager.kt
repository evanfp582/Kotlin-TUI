package tui

import jline.TerminalFactory
import jline.console.ConsoleReader
import tui.components.Component
import kotlinx.coroutines.*
import tui.components.TextBox


class UIManager(private var screenObject: ScreenObject) {
    private val components= mutableListOf<Component>()
    private val staticComponents= mutableListOf<Component>()

    fun addComponent(component: Component) { components.add(component) }
    fun addStaticComponent(component: Component) { staticComponents.add(component) }
    fun renderAll() { components.forEach { it.render() } }

    suspend fun run() {
        Terminal.hideCursor()
        Terminal.clear()

        val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())

        val renderJob = scope.launch {
            staticComponents.forEach { it.render() }
            while (isActive) {
                renderAll()
                screenObject.render()
                delay(50)
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
                        components.forEach { it.handleInput(ch.toChar()) }
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