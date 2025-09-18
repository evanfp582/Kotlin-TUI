package tui.components

import tui.DebugLogger
import tui.ScreenObject
import kotlin.math.round

class Title(
    override var screenObject: ScreenObject,
    override var row: Int,
    override var col: Int?,
    private var text: String,
//    private var centered: Boolean=true,
    private var style: String=""
): Component {

    private var lines = text.lines()
    private var height = lines.size
    private var width = lines.maxOf { it.length }
    override val centeredStartingPoint = (screenObject.terminalWidth / 2) - (width / 2)
    override var isDirty: Boolean = true
    override val area: Area = Area(row, col ?: centeredStartingPoint, height, width)

    init {
        if (width > screenObject.terminalWidth){
            throw IllegalArgumentException(
                "Title with width ($width) exceeds terminal width of (${screenObject.terminalWidth})"
                )
        }
        if (height > screenObject.terminalHeight){
            throw IllegalArgumentException(
                "Title with width ($height) exceeds terminal width of (${screenObject.terminalHeight})"
            )
        }
    }

    fun forceRerender() {
        isDirty = true
        render()
        isDirty = false
    }

    override fun render(){
        if (isDirty) {
            text.lines().forEachIndexed { index, line ->
                screenObject.setString(row+index, col ?: centeredStartingPoint,  line, style)
            }
            isDirty = false
        }
    }
    override fun handleInput(key: Char) = Unit
}
