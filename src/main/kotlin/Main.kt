import kotlinx.coroutines.*
import tui.Ansi
import tui.ScreenObject
import tui.Terminal
import tui.UIManager
import tui.components.Select
import tui.components.Text
import tui.components.TextBox

fun main(): Unit = runBlocking {
    val screenObject = ScreenObject(15, 100)
    val ui = UIManager(screenObject)
    ui.addStaticComponent(Text(screenObject, 1, 0, "Welcome to Kotlin TUI", "${Ansi.TextStyles.BOLD}${Ansi.TextStyles.BLINK}"))
    ui.addStaticComponent(Text(screenObject, 2, 0, "This will be a demo of what you can do"))
//    ui.addComponent(TextBox(screenObject, 4, 0, 25, "Type Here"))
    ui.addComponent(Select(screenObject, 4,0, listOf("First", "THIS ONE IS MUCH LONGER", "Third")))
    ui.run()
}
