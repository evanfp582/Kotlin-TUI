package tui.components

interface ControllableComponent: Component {
    var isFocused: Boolean

    fun onFocus() {
        isFocused = true
        isDirty = true
    }

    fun onUnfocus() {
        isFocused = false
        isDirty = true
    }

    fun handleInput(ch: Char)
    object Keybinds {
        const val UP = 'w'
        const val DOWN = 's'
        const val WINDOWS_ENTER = '\r'
        const val LINUX_ENTER = '\u000A'
    }
}