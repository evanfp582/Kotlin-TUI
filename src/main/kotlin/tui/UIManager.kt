package tui

import tui.components.Component
import kotlinx.coroutines.*


class UIManager {
    private val components= mutableListOf<Component>()

    fun addComponent(component: Component) {
        components.add(component)
    }

    fun renderAll() {
        components.forEach { it.render() }
    }

    suspend fun run(block: suspend () -> Unit = {}) {
        Terminal.hideCursor()
        Terminal.clear()

        val renderJob = CoroutineScope(Dispatchers.Default).launch {
            while (isActive) {
                renderAll()
                delay(100)
            }
        }

        val inputJob = CoroutineScope(Dispatchers.IO).launch {
            while (isActive) {
                val key = System.`in`.read().toChar()
                when (key) {
                    '\u001B' -> continue
                    '\r' -> { }
                    'q' -> break
                }
            }
        }

        block()  // optional logic

        renderJob.cancelAndJoin()
        inputJob.cancelAndJoin()

        Terminal.showCursor()
        Terminal.clear()
    }
}