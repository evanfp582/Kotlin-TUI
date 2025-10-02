package tui.components

import tui.DebugLogger
import tui.ScreenObject

class TextBox(
    override var screenObject: ScreenObject,
    override var row: Int,
    override var col: Int?,
    private val width: Int,
    var text: String = ""
): ControllableComponent {
    override var isDirty: Boolean = true
    override var isFocused: Boolean = false
    override val area: Area = Area(row, col ?: 0, 1, width)
    override val centeredStartingPoint = (screenObject.terminalWidth / 2) - (width / 2)

    override fun render() {
        if (isDirty){
            var fullText: String = text
            if (isFocused) {
                fullText += "|"
            }
            val displayText = fullText.padEnd(width)
            screenObject.setString(row, col ?: 0, "[$displayText]")
            isDirty = false
        }
    }

    override fun handleInput(ch: Char) {
        if (isFocused) {
            when (ch.code) {
                8, 127 -> {
                    if (text.isNotEmpty()) {
                        text = text.dropLast(1)
                    }
                }
                13 -> { // Enter
                    DebugLogger.log("Select Text: $text")
                }
                else -> {
                    if (!ch.isISOControl() && text.length < width) {
                        text += ch
                    }
                }
            }
            isDirty = true
        }
    }


}