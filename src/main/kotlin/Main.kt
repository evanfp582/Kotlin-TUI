import kotlinx.coroutines.*
import tui.Ansi
import tui.Terminal
import tui.UIManager
import tui.components.Select
import tui.components.Text
import tui.components.TextBox

fun main(): Unit = runBlocking {
    val ui = UIManager()
    ui.addStaticComponent(Text(1, 0, "Welcome to Kotlin TUI", "${Ansi.TextStyles.BOLD}${Ansi.TextStyles.BLINK}"))
    ui.addStaticComponent(Text(2, 0, "This will be a demo of what you can do"))
    ui.addComponent(TextBox(4,0, 25, "Start"))
    ui.run({while (true){delay(500)} })
}
