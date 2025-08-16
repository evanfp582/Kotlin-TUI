import kotlinx.coroutines.*
import tui.Terminal
import tui.UIManager
import tui.components.Text

fun main(): Unit = runBlocking {
    val ui = UIManager()
    ui.addStaticComponent(Text(1, 0, "Kotlin TUI"))
    ui.addStaticComponent(Text(2, 0, "Hello this is example text"))
    ui.addComponent(Text(3,0, "Number of times rendered: "))
    ui.run({while (true){delay(500)} })
}
