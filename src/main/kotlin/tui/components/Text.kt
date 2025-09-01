package tui.components

import tui.ScreenObject

class Text(
    override var screenObject: ScreenObject,
    override var row: Int,
    override var col: Int,
    private var text: String,
    private var style: String=""
): Component {
    /*
    Plain text element
    */
    override val area: Area = Area(row, col, 1, text.length)
    override fun render(){ screenObject.setString(row,col, text, style) }
    override fun handleInput(key: Char): Unit {}

}
