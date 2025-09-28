package tui.components

import tui.DebugLogger
import tui.ScreenObject

class Button(
    override var screenObject: ScreenObject,
    override var row: Int,
    override var col: Int?,
    var width: Int,
): Component {

    var hasControl: Boolean = true
    var isPressed: Boolean = false

    override val centeredStartingPoint = (screenObject.terminalWidth / 2) - (width / 2)

    override val area: Area = Area(row, col ?: centeredStartingPoint, width, width)

    override var isDirty: Boolean = true

    override fun render() {
        TODO("Not yet implemented")
    }

    override fun handleInput(key: Char) {
        when (key) {
            Component.Keybinds.WINDOWS_ENTER, Component.Keybinds.LINUX_ENTER -> {
                DebugLogger.log("Button Pressed")
            }
        }
        isDirty = true
    }

//    Width = 8
//    Height = 3

    val regularButton = """
+------+
|Button|
+------+""".trimIndent()


}