import kotlinx.coroutines.*
import tui.Ansi
import tui.ScreenObject
import tui.Terminal
import tui.UIManager
import tui.components.Select
import tui.components.Text
import tui.components.TextBox
import tui.components.Title
import kotlin.math.round

fun main(): Unit = runBlocking {
    val screenObject = ScreenObject()
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
    ui.addComponent(Title(screenObject, 3, null, title))
    ui.addComponent(Text(screenObject, 15, null, "Select where you'd like to go!", "${Ansi.TextStyles.BOLD}${Ansi.TextStyles.UNDERLINE}"))
    ui.addComponent(Select(screenObject, 16,null,listOf("Programs", "Test Page", "README")))
    ui.run()
}
