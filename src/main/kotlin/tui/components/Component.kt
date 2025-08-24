package tui.components

import tui.ScreenObject

interface Component {
    var row: Int
    var col: Int
    fun render(screenObject: ScreenObject)
    fun handleInput(key: Char)
    object Keybinds {
        const val UP = 'w'
        const val DOWN = 's'
        const val WINDOWS_ENTER = '\r'
        const val LINUX_ENTER = '\u000A'
    }
}