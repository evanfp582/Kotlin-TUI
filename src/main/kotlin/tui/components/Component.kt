package tui.components

interface Component {
    var row: Int
    var col: Int
    fun render()
    fun handleInput(key: Char)
}