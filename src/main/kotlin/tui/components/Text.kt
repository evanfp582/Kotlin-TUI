package tui.components

import tui.Terminal

class Text(override var row: Int, override var col: Int, private var text: String): Component {
    /*
    Plain text element
    */
    var renderCount = 0
    override fun render(){
        Terminal.printAt(row, col, "$text $renderCount")
        renderCount += 1
    }
    override fun handleInput(key: Char): Unit {}

}
