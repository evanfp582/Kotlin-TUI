package tui.components

import tui.Terminal

class TextBox(
    override var row: Int,
    override var col: Int,
    private val width: Int,
    private var text: String = ""
): Component {
    override fun render() {
        val displayText = text.padEnd(width)
        Terminal.printAt(row, col, "[ $displayText ]")
    }

    override fun handleInput(key: Char) {
        return
    }

    fun setText(newText: String) {
        text = newText.take(width)
        render()
    }

}