import kotlinx.coroutines.*
import tui.Ansi
import tui.ScreenObject
import tui.UIManager
import tui.components.Select
import tui.components.Text
import tui.components.TextBox
import tui.components.Title

fun main(): Unit = runBlocking {

    val ui = UIManager()

    // Title Screen
    ui.addScreen(
        ScreenObject().apply {
            addComponents(arrayOf(
                Title(this, 3, null, title),
                Text(this, 15, null, "Select where you'd like to go!", "${Ansi.TextStyles.BOLD}${Ansi.TextStyles.UNDERLINE}"),
                Select(this, 16, null, listOf("Programs", "Test Page", "README"))
            ))
        }
    )

    //Screen 2- Readme
//    ui.addScreen(
//        ScreenObject().apply {
//            addComponents(arrayOf(
//                Title(this, 3, null, "Welcome to the README page"),
//                TextBox(this, 5,0, 40, "Is this default string?")
//            ))
//        }
//    )

    ui.run()
}

val title ="""
█████   ████           █████    ████   ███                 ███████████ █████  █████ █████ 
░░███   ███░           ░░███    ░░███  ░░░                ░█░░░███░░░█░░███  ░░███ ░░███ 
 ░███  ███     ██████  ███████   ░███  ████  ████████     ░   ░███  ░  ░███   ░███  ░███  
 ░███████     ███░░███░░░███░    ░███ ░░███ ░░███░░███        ░███     ░███   ░███  ░███  
 ░███░░███   ░███ ░███  ░███     ░███  ░███  ░███ ░███        ░███     ░███   ░███  ░███  
 ░███ ░░███  ░███ ░███  ░███ ███ ░███  ░███  ░███ ░███        ░███     ░███   ░███  ░███  
 █████ ░░████░░██████   ░░█████  █████ █████ ████ █████       █████    ░░████████   █████ 
 ░░░░░   ░░░░  ░░░░░░     ░░░░░  ░░░░░ ░░░░░ ░░░░ ░░░░░       ░░░░░      ░░░░░░░░   ░░░░░ """.trimIndent()