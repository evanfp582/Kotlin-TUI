package tui

import jline.TerminalFactory
import jline.console.ConsoleReader
import tui.components.Component
import kotlinx.coroutines.*


class UIManager {
    private val components= mutableListOf<Component>()
    private val staticComponents= mutableListOf<Component>()

    fun addComponent(component: Component) { components.add(component) }
    fun addStaticComponent(component: Component) { staticComponents.add(component) }
    fun renderAll() { components.forEach { it.render() } }

    suspend fun run(block: suspend () -> Unit = {}) {
        Terminal.hideCursor()
        Terminal.clear()

        val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())

        val renderJob = scope.launch {
            staticComponents.forEach { it.render() }
            while (isActive) {
                renderAll()
                delay(100)
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