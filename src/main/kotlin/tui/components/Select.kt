package tui.components

import tui.ScreenObject

class Select(
    override var screenObject: ScreenObject,
    override var row: Int,
    override var col: Int?,
    private var options: List<String>,
    private var onEnter: (String) -> Unit = { choice ->
        var largestLine = options.maxOf { it.length + 3 }
        val centeredStartingPoint = (screenObject.terminalWidth / 2) - (largestLine / 2)
        val prefix = "Selected "
        screenObject.cleanRow(
            row + options.size,
            (col ?: centeredStartingPoint)+options.maxOf { it.length + prefix.length },
            (col ?: centeredStartingPoint)
        )
        screenObject.setString(row + options.size, (col ?: centeredStartingPoint), "$prefix$choice")
    }
): Component {
    override var isDirty: Boolean = true
    private val prefix = "Selected "
    private val largestLine = options.maxOf { it.length + prefix.length}
    override val area: Area = Area(row, col ?: 0, options.size + 1, largestLine)
    override val centeredStartingPoint = (screenObject.terminalWidth / 2) - (largestLine / 2)
    private var highlightedIndex = 0


    override fun handleInput(key: Char) {
        when (key) {
            Component.Keybinds.UP -> if (highlightedIndex > 0) highlightedIndex--
            Component.Keybinds.DOWN -> if (highlightedIndex < options.size - 1) highlightedIndex++
            Component.Keybinds.WINDOWS_ENTER, Component.Keybinds.LINUX_ENTER -> onEnter(options[highlightedIndex])
        }
        isDirty = true
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
}