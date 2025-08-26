package tui.components

import tui.ScreenObject
import tui.Terminal

class TextBox(
    override var row: Int,
    override var col: Int,
    private val width: Int,
    var text: String = ""
): Component {

    var hasControl: Boolean = true

    override fun render(screenObject: ScreenObject) {
        val displayText = text.padEnd(width)
//        Terminal.printAt(row, col, "[ $displayText ]")
        screenObject.setString(row, col, "[$displayText]")
        if (hasControl) {
            Terminal.moveCursor(row, col + 3 + text.length)
            Terminal.showCursor()
        }
    }

    override fun handleInput(key: Char) {
        if (!hasControl) return
        when (key.code) {
            8, 127 -> {
                if (text.isNotEmpty()) {
                    text = text.dropLast(1)
                }
            }

            else -> {
                if (!key.isISOControl() && text.length < width) {
                    text += key
                }
            }
        }
    }


}