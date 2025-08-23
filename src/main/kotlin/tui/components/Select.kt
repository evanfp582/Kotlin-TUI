package tui.components

import tui.Terminal

class Select(
    override var row: Int,
    override var col: Int,
    private var options: List<String>,
    private var onEnter: (String) -> Unit = { choice ->
        Terminal.clearRow(row + options.size)
        Terminal.printAt(row + options.size, 0, "You picked $choice")}
): Component {

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
            Terminal.printAt(row + i, col, "$prefix $option")
        }
    }
}