package tui

import jline.TerminalFactory
import jline.console.ConsoleReader
import tui.components.Component
import kotlinx.coroutines.*
import tui.components.TextBox


class UIManager {
    private val components= mutableListOf<Component>()
    private val staticComponents= mutableListOf<Component>()
    private val screenObject= ScreenObject(10, 100)

    fun addComponent(component: Component) { components.add(component) }
    fun addStaticComponent(component: Component) { staticComponents.add(component) }
    fun renderAll() { components.forEach { it.render(screenObject) } }

    suspend fun run(block: suspend () -> Unit = {}) {
        Terminal.hideCursor()
        Terminal.clear()

        val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())

        val renderJob = scope.launch {
            staticComponents.forEach { it.render(screenObject) }
            while (isActive) {
                renderAll()
                // Restore cursor after all components rendered
                components.filterIsInstance<TextBox>()
                    .firstOrNull { it.hasControl }
                    ?.let { tb ->
                        Terminal.moveCursor(tb.row, tb.col + 3 + tb.text.length)
                        Terminal.showCursor()
                    }
                screenObject.render()
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