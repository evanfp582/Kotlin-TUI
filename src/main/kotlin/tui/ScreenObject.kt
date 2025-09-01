package tui

import jline.TerminalFactory
import kotlin.Array

class ScreenObject(val rows: Int, val cols: Int) {
    private val terminal: jline.Terminal = TerminalFactory.get()
    val terminalWidth = terminal.width
    val terminalHeight = terminal.height

    private val buffer: Array<CharArray> = Array(terminalHeight) { CharArray(terminalWidth) { ' ' } }
    private val styleBuffer: Array<Array<String?>> = Array(terminalHeight) { Array(terminalWidth) { null } }

    private val diffArray: Array<Boolean> = Array(terminalHeight) {false}

    fun setString(row: Int, col: Int, string: String, style: String = "") {
        diffArray[row] = true
        for ((index, char) in string.withIndex()) {
            if (col + index < terminalWidth) {
                buffer[row][col + index] = char
                styleBuffer[row][col + index] = style
            }
            else{
                throw IllegalArgumentException(
                    "Tried to set component with string ${string}outside width of terminal ($terminalWidth)"
                )
            }
        }
    }

    fun cleanRow(row:Int, col:Int, startingCol:Int=0){
        for (index in startingCol..col) {
            buffer[row][index] = ' '
            styleBuffer[row][index] = null
        }
    }

    fun render() {
        for ((rowIndex, dirty) in diffArray.withIndex()){
            if (dirty) {
                val str = StringBuilder()
                var currentStyle: String? = null

                for (col in 0 until terminalWidth) {
                    val style = styleBuffer[rowIndex][col]
                    if (style != currentStyle) {
                        if (currentStyle != null) { str.append(Ansi.TextStyles.RESET) }
                        if (style != null) { str.append(style) }
                        currentStyle = style
                    }
                    str.append(buffer[rowIndex][col])
                }

                if (currentStyle != null) str.append(Ansi.TextStyles.RESET)
                Terminal.printAt(rowIndex, 0, str.toString())
                diffArray[rowIndex] = false
            }
        }
    }

}