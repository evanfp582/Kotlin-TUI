package tui

import jline.TerminalFactory
import tui.components.Component
import kotlin.Array

class ScreenObject() {
    private val terminal: jline.Terminal = TerminalFactory.get()
    val terminalWidth = terminal.width
    val terminalHeight = terminal.height

    // What I consider to be the "state" of the screen
    private val buffer: Array<CharArray> = Array(terminalHeight) { CharArray(terminalWidth) { ' ' } }
    private val styleBuffer: Array<Array<String?>> = Array(terminalHeight) { Array(terminalWidth) { null } }
    private val ownershipMatrix: Array<Array<Int?>> = Array(terminalHeight) { Array<Int?>(terminalWidth) { null } }
    private val diffArray: Array<Boolean> = Array(terminalHeight) {false}

    private var globalFlagArray: BooleanArray? = null
    private var myFlag: Int? = null

    val components = mutableListOf<Component>()

    fun renderAll() { components.forEach { it.render() } }

    fun setFlag(flagArray: BooleanArray, flag: Int){
        globalFlagArray = flagArray
        myFlag = flag
    }

    fun addComponent(component: Component) {
        val area = component.area
        // Check if area fits in screen
        if (area.row + area.height > terminalHeight ||
            area.col + area.width > terminalWidth) {
            error("Component $component is too big for the screen")
        }
        // Check for overlap
        for (row in area.row until area.row + area.height) {
            for (col in area.col until area.col + area.width) {
                if (ownershipMatrix[row][col] != null) { error("Component $component overlaps another component at ($row, $col)") }
                ownershipMatrix[row][col] = components.size
            }
        }
        components.add(component)
    }
    fun addComponents(components: Array<Component>){ components.forEach { addComponent(it) }}

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