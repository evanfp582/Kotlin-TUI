package tui.components

import tui.ScreenObject
import tui.Terminal

class TextBox(
    override var screenObject: ScreenObject,
    override var row: Int,
    override var col: Int?,
    private val width: Int,
    var text: String = ""
): Component {
    override val area: Area = Area(row, col ?: 0, 1, width)
    override val centeredStartingPoint = (screenObject.terminalWidth / 2) - (width / 2)
    var hasControl: Boolean = true

    override fun render() {
        var fullText: String = text
        if (hasControl) {
            fullText += "|"
        }
        val displayText = fullText.padEnd(width)
        screenObject.setString(row, col ?: 0, "[$displayText]")
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