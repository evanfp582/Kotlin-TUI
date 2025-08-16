import kotlinx.coroutines.*
import tui.UIManager
import tui.components.Select
import tui.components.Text

fun main(): Unit = runBlocking {
    val ui = UIManager()
    ui.addStaticComponent(Text(1, 0, "Kotlin TUI"))
    ui.addStaticComponent(Text(2, 0, "Hello this is example text"))
    ui.addComponent(Text(3,0, "Number of times rendered: "))
    ui.addComponent(Select(4,0,listOf("First", "Second", "Third")))
    ui.run({while (true){delay(500)} })
}
