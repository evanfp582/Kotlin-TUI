import kotlinx.coroutines.*
import tui.ScreenObject
import tui.Terminal
import tui.UIManager
import tui.components.Select
import tui.components.Text
import tui.components.TextBox
import tui.components.Title

fun main(): Unit = runBlocking {
    val screenObject = ScreenObject(35, 100)
    val ui = UIManager(screenObject)
//    ui.addComponent(TextBox(screenObject, 4, 0, 25, "Type Here"))
//    ui.addComponent(Select(screenObject, 4,0, listOf("First", "THIS ONE IS MUCH LONGER", "Third")))
    val title ="""
█████   ████           █████    ████   ███                 ███████████ █████  █████ █████ 
░░███   ███░           ░░███    ░░███  ░░░                ░█░░░███░░░█░░███  ░░███ ░░███ 
 ░███  ███     ██████  ███████   ░███  ████  ████████     ░   ░███  ░  ░███   ░███  ░███  
 ░███████     ███░░███░░░███░    ░███ ░░███ ░░███░░███        ░███     ░███   ░███  ░███  
 ░███░░███   ░███ ░███  ░███     ░███  ░███  ░███ ░███        ░███     ░███   ░███  ░███  
 ░███ ░░███  ░███ ░███  ░███ ███ ░███  ░███  ░███ ░███        ░███     ░███   ░███  ░███  
 █████ ░░████░░██████   ░░█████  █████ █████ ████ █████       █████    ░░████████   █████ 
 ░░░░░   ░░░░  ░░░░░░     ░░░░░  ░░░░░ ░░░░░ ░░░░ ░░░░░       ░░░░░      ░░░░░░░░   ░░░░░ """.trimIndent()
    ui.addComponent(Title(screenObject, 3, 25, title))
    ui.addComponent(Select(screenObject, 7,0,listOf("One Big Ole Word to te", "Two", "Three")))
    ui.run()
}
