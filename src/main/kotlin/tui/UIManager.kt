package tui

import jline.TerminalFactory
import jline.console.ConsoleReader
import tui.components.Component
import kotlinx.coroutines.*
import tui.components.TextBox


class UIManager(private var screenObject: ScreenObject) {

    private val ownershipMatrix: Array<Array<Int?>> = Array(screenObject.terminalHeight) { Array<Int?>(screenObject.terminalWidth) { null } }

    private val components= mutableListOf<Component>()
    private val staticComponents= mutableListOf<Component>()

    fun addComponent(component: Component) {
        val area = component.area
        // Check if area fits in screen
        if (area.row + area.height > screenObject.terminalHeight ||
            area.col + area.width > screenObject.terminalWidth) {
            error("Component $component is too big for the screen")
        }

        // Check for overlap
        for (row in area.row until area.row + area.height) {
            for (col in area.col until area.col + area.width) {
                if (ownershipMatrix[row][col] != null) { error("Component $component overlaps another component at ($row, $col)") }
                ownershipMatrix[row][col] = components.size
            }
        }
        components.add(component)
    }

    fun clearRowInComponent(component: Component, row: Int) {
        //Assume row is a valid row for the component
        screenObject.cleanRow(row, (component.col ?: 0)+component.area.width, (component.col ?: 0))
    }

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