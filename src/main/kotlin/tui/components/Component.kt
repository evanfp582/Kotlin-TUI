package tui.components

import tui.ScreenObject

interface Component {
    var screenObject: ScreenObject
    var row: Int
    var col: Int?
    val area: Area
    val centeredStartingPoint: Int
    fun render()
    fun handleInput(key: Char)
    object Keybinds {
        const val UP = 'w'
        const val DOWN = 's'
        const val WINDOWS_ENTER = '\r'
        const val LINUX_ENTER = '\u000A'
    }
}
data class Area(val row: Int, val col: Int, val height: Int, val width: Int)