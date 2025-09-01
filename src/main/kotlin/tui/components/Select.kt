package tui.components

import tui.ScreenObject

class Select(
    override var screenObject: ScreenObject,
    override var row: Int,
    override var col: Int,
    private var options: List<String>,
    private var onEnter: (String) -> Unit = { choice ->
        screenObject.cleanRow(row + options.size, col+options.maxOf { it.length + 3 }, 0)
        screenObject.setString(row + options.size, 0, "-> $choice")
    }
): Component {
    override val area: Area = Area(row, col, options.size + 1, options.maxOf { it.length + 3 })
    private var highlightedIndex = 0


    override fun handleInput(key: Char) {
        when (key) {
            Component.Keybinds.UP -> if (highlightedIndex > 0) highlightedIndex--
            Component.Keybinds.DOWN -> if (highlightedIndex < options.size - 1) highlightedIndex++
            Component.Keybinds.WINDOWS_ENTER, Component.Keybinds.LINUX_ENTER -> onEnter(options[highlightedIndex])
        }
    }

    override fun render() {
        options.forEachIndexed { i, option ->
            val prefix = if (i == highlightedIndex) ">" else " "
            screenObject.setString(row+i, col, "$prefix $option")

        }
    }
}