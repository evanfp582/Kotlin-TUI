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

    object Keybinds {
        const val UP = 'w'
        const val DOWN = 's'
    }

    override fun handleInput(key: Char) {
        when (key) {
            Keybinds.UP -> if (highlightedIndex > 0) highlightedIndex--
            Keybinds.DOWN -> if (highlightedIndex < options.size - 1) highlightedIndex++
            '\u000A', '\r' -> onEnter(options[highlightedIndex])
        }
    }

    override fun render() {
        options.forEachIndexed { i, option ->
            val prefix = if (i == highlightedIndex) ">" else " "
            Terminal.printAt(row + i, col, "$prefix $option")
        }
    }
}