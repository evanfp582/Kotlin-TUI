package tui.components

import tui.Ansi
import tui.DebugLogger
import tui.ScreenObject

class ToggleButton(
    override var screenObject: ScreenObject,
    override var row: Int,
    override var col: Int?,
    var isPressed: Boolean = false
) : ControllableComponent {
    val toggleSwitch = arrayOf("""    
                                              
                  █████████████████          
             ███████████████████████████     
           █████████████████           ███   
          █████████████████ __  ___ ___ ███  
         ██████████████████[__] |-- |-- ████ 
          █████████████████             ███  
           █████████████████           ███   
             ███████████████████████████     
                  █████████████████          
                                              
                  """.trimIndent().prependIndent(" "),
        """
                                             
                 █████████████████          
            ███████████████████████████     
          ███           █████████████████   
         ███   __  __ _  █████████████████  
        ████  [__] | \|  ██████████████████ 
         ███             █████████████████  
          ███           █████████████████   
            ███████████████████████████     
                 █████████████████          
                                             
""".trimIndent().prependIndent(" ")
    )
    override var isFocused: Boolean = false
    override var isDirty: Boolean = true
    var currentGraphic = if (isPressed) toggleSwitch[1] else toggleSwitch[0]

    private var lines = currentGraphic.lines()
    private var height = lines.size
    private var width = lines.maxOf { it.length }

    override val centeredStartingPoint = (screenObject.terminalWidth / 2) - (width / 2)

    override val area: Area = Area(row, col ?: centeredStartingPoint, height, width)

    init {
        validateSize()
    }

    override fun handleInput(ch: Char) {
        if (isFocused) {
            when (ch) {
                ControllableComponent.Keybinds.WINDOWS_ENTER, ControllableComponent.Keybinds.LINUX_ENTER -> {
                    DebugLogger.log("Toggle Button going from $isPressed to ${!isPressed}")
                    isPressed = !isPressed
                    currentGraphic = if (isPressed) toggleSwitch[1] else toggleSwitch[0]
                }
            }
            isDirty = true
        }
    }

    override fun render() {
        if (isDirty) {
            currentGraphic.lines().forEachIndexed { index, line ->
                if (isFocused){
                    screenObject.setString(row+index, col ?: centeredStartingPoint,  line, Ansi.TextStyles.REVERSE)
                }else {
                    screenObject.setString(row+index, col ?: centeredStartingPoint,  line)

                }
            }
            isDirty = false
        }
    }
}




