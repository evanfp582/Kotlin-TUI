package tui.components

import tui.ScreenObject

class Select(
    override var screenObject: ScreenObject,
    override var row: Int,
    override var col: Int?,
    private var options: List<String>,
    private var onEnter: (String) -> Unit = { choice -> defaultOnEnter(screenObject, row, col, options, choice) }
): ControllableComponent {
    override var isDirty: Boolean = true
    override var isFocused: Boolean = false
    private val prefix = "Selected "
    private val largestLine = options.maxOf { it.length + prefix.length}
    override val area: Area = Area(row, col ?: 0, options.size + 1, largestLine)
    override val centeredStartingPoint = (screenObject.terminalWidth / 2) - (largestLine / 2)
    private var highlightedIndex = 0


    override fun handleInput(ch: Char) {
        if (isFocused) {
            when (ch) {
                ControllableComponent.Keybinds.UP -> if (highlightedIndex > 0) highlightedIndex--
                ControllableComponent.Keybinds.DOWN -> if (highlightedIndex < options.size - 1) highlightedIndex++
                ControllableComponent.Keybinds.WINDOWS_ENTER, ControllableComponent.Keybinds.LINUX_ENTER -> onEnter(options[highlightedIndex])
            }
            isDirty = true
        }
    }

    override fun render() {
        if (isDirty) {
            options.forEachIndexed { i, option ->
                val prefix = if (i == highlightedIndex) ">" else " "
                screenObject.setString(row + i, col ?: centeredStartingPoint, "$prefix $option")
            }
        }
        isDirty = false
    }

    companion object {
        fun defaultOnEnter(
            screenObject: ScreenObject,
            row: Int,
            col: Int?,
            options: List<String>,
            choice: String
        ) {
            val prefix = "Selected "
            val largestLine = options.maxOf { it.length + prefix.length }
            val centeredStartingPoint = (screenObject.terminalWidth / 2) - (largestLine / 2)

            screenObject.cleanRow(
                row + options.size,
                (col ?: centeredStartingPoint) + largestLine,
                (col ?: centeredStartingPoint)
            )
            screenObject.setString(row + options.size, (col ?: centeredStartingPoint), "$prefix$choice")
        }
    }

}

