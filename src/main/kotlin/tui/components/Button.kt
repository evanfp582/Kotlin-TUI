package tui.components

import tui.Ansi
import tui.DebugLogger
import tui.ScreenObject

class Button(
    override var screenObject: ScreenObject,
    override var row: Int,
    override var col: Int?,
    val buttonText: String = """
+------+
|Button|
+------+""".trimIndent(),
): ControllableComponent {
    override var isFocused: Boolean = false

    private var lines = buttonText.lines()
    private var height = lines.size
    private var width = lines.maxOf { it.length }

    override val centeredStartingPoint = (screenObject.terminalWidth / 2) - (width / 2)

    override val area: Area = Area(row, col ?: centeredStartingPoint, height, width)

    override var isDirty: Boolean = true


    init {
        validateSize()
    }

    override fun render(){
        if (isDirty) {
            buttonText.lines().forEachIndexed { index, line ->
                if (isFocused){
                    screenObject.setString(row+index, col ?: centeredStartingPoint,  line, Ansi.TextStyles.REVERSE)
                }else {
                    screenObject.setString(row+index, col ?: centeredStartingPoint,  line)

                }
            }
            isDirty = false
        }
    }

    override fun handleInput(ch: Char) {
        if (isFocused) {
            when (ch) {
                ControllableComponent.Keybinds.WINDOWS_ENTER, ControllableComponent.Keybinds.LINUX_ENTER -> {
                    DebugLogger.log("Button Pressed")
                }
            }
            isDirty = true
        }
    }

//    Width = 8
//    Height = 3

    val regularButton = """
+------+
|Button|
+------+""".trimIndent()


}