package tui.components

import tui.ScreenObject

class Title(
    override var screenObject: ScreenObject,
    override var row: Int,
    override var col: Int,
    private var text: String,
//    private var centered: Boolean=true,
    private var style: String=""
): Component {

    private var lines = text.lines()
    private var height = lines.size
    private var width = lines.maxOf { it.length }

    override val area: Area = Area(row, col, height, width)

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


    override fun render(){
        text.lines().forEachIndexed { index, line ->
            screenObject.setString(row+index, col,  line, style)
        }
    }
    override fun handleInput(key: Char) = Unit
}
