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
    val screenRouter: Map<String, Int> = mapOf(
        "Programs" to 1,
        "Test Page" to 2,
        "README" to 3
    )

    // Screen 1 Title Screen
    ui.addScreen(
        ScreenObject().apply {

            addComponents(arrayOf(
                Title(this, 3, null, title),
                Text(this, 15, null, "Select where you'd like to go!", "${Ansi.TextStyles.BOLD}${Ansi.TextStyles.UNDERLINE}"),
                Select(this, 16, null, listOf("Programs", "Test Page", "README"), { choice: String ->
                    print(Ansi.Screen.CLEAR_SCREEN)
                    ui.flagArray[screenRouter[choice] ?: 1] = true
                })
            ))
        }
    )
    //Screen 2- Programs
    ui.addScreen(
        ScreenObject().apply {
            addComponents(arrayOf(
                Title(this, 3, null, "Welcome to the Programs page"),
            ))
        }
    )

    //Screen 3- Readme
    ui.addScreen(
        ScreenObject().apply {
            addComponents(arrayOf(
                Title(this, 3, null, "Welcome to the Test Page page"),
            ))
        }
    )

    //Screen 4- Readme
    ui.addScreen(
        ScreenObject().apply {
            addComponents(arrayOf(
                Title(this, 3, null, "Welcome to the README page"),
            ))
        }
    )

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