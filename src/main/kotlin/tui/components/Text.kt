package tui.components

import tui.Ansi
import tui.ScreenObject
import tui.Terminal

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
    override fun render(){
        screenObject.setString(row,col, "$style$text${Ansi.TextStyles.RESET}")
    }
    override fun handleInput(key: Char): Unit {}

}
