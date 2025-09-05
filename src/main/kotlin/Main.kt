import kotlinx.coroutines.*
import tui.Ansi
import tui.ScreenObject
import tui.UIManager
import tui.components.Select
import tui.components.Text
import tui.components.Title

fun main(): Unit = runBlocking {
    val titleScreen = ScreenObject()
    val ui = UIManager()
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
    titleScreen.addComponents(arrayOf(
        Title(titleScreen, 3, null, title),
        Text(titleScreen, 15, null, "Select where you'd like to go!", "${Ansi.TextStyles.BOLD}${Ansi.TextStyles.UNDERLINE}"),
        Select(titleScreen, 16,null,listOf("Programs", "Test Page", "README"))
    ))

    ui.addScreen(titleScreen)
    ui.run()
}
