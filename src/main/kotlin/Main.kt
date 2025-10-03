import kotlinx.coroutines.*
import tui.Ansi
import tui.ScreenObject
import tui.UIManager
import tui.components.Button
import tui.components.Select
import tui.components.Text
import tui.components.TextBox
import tui.components.Title

fun main(): Unit = runBlocking {

    val ui = UIManager()
    val screenRouter: Map<String, Int> = mapOf(
        "Service Dashboard" to 1,
        "Applications" to 2,
        "README" to 3
    )

    // Screen 1 Title Screen
    ui.addScreen(
        ScreenObject().apply {

            addComponents(arrayOf(
                Title(this, 2, null, title),
                Text(this, 20, null, "Select where you'd like to go!", "${Ansi.TextStyles.BOLD}${Ansi.TextStyles.UNDERLINE}"),
                Select(this, 21, null, listOf("Service Dashboard", "Applications", "README"), { choice: String ->
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
                Title(this, 3, null, "Welcome to the Service Dashboard page"),
                Button(this, 10, 1),
                Button(this, 10, 20),
                Button(this, 10, 40),
                Button(this, 10, 60),
            ))
        }
    )

    //Screen 3- Readme
    ui.addScreen(
        ScreenObject().apply {
            addComponents(arrayOf(
                Title(this, 3, null, "Welcome to the Applications page"),
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
▐▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▌
▐         _____              _              _             ▌
▐        |  __ \            (_)            | |            ▌
▐        | |__) |_ __  ___   _   ___   ___ | |_           ▌
▐        |  ___/| '__|/ _ \ | | / _ \ / __|| __|          ▌
▐        | |    | |  | (_) || ||  __/| (__ | |_           ▌
▐        |_|    |_|   \___/ | | \___| \___| \__|          ▌
▐ _    _                   _/ |   _                       ▌
▐| |  | |                 |__/   | |                      ▌
▐| |__| |  ___   _ __ ___    ___ | |__    __ _  ___   ___ ▌
▐|  __  | / _ \ | '_ ` _ \  / _ \| '_ \  / _` |/ __| / _ \▌
▐| |  | || (_) || | | | | ||  __/| |_) || (_| |\__ \|  __/▌
▐|_|  |_| \___/ |_| |_| |_| \___||_.__/  \__,_||___/ \___|▌
▐▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▌""".trimIndent()

/**
 * Alternate
 * ▐▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▌
 * ▐          ███████████                          ███                     █████         ▌
 * ▐         ░░███░░░░░███                        ░░░                     ░░███          ▌
 * ▐          ░███    ░███ ████████   ██████      █████  ██████   ██████  ███████        ▌
 * ▐          ░██████████ ░░███░░███ ███░░███    ░░███  ███░░███ ███░░███░░░███░         ▌
 * ▐          ░███░░░░░░   ░███ ░░░ ░███ ░███     ░███ ░███████ ░███ ░░░   ░███          ▌
 * ▐          ░███         ░███     ░███ ░███     ░███ ░███░░░  ░███  ███  ░███ ███      ▌
 * ▐          █████        █████    ░░██████      ░███ ░░██████ ░░██████   ░░█████       ▌
 * ▐         ░░░░░        ░░░░░      ░░░░░░       ░███  ░░░░░░   ░░░░░░     ░░░░░        ▌
 * ▐                                          ███ ░███                                   ▌
 * ▐                                         ░░██████                                    ▌
 * ▐                                          ░░░░░░                                     ▌
 * ▐ █████   █████                                   █████                               ▌
 * ▐░░███   ░░███                                   ░░███                                ▌
 * ▐ ░███    ░███   ██████  █████████████    ██████  ░███████   ██████    █████   ██████ ▌
 * ▐ ░███████████  ███░░███░░███░░███░░███  ███░░███ ░███░░███ ░░░░░███  ███░░   ███░░███▌
 * ▐ ░███░░░░░███ ░███ ░███ ░███ ░███ ░███ ░███████  ░███ ░███  ███████ ░░█████ ░███████ ▌
 * ▐ ░███    ░███ ░███ ░███ ░███ ░███ ░███ ░███░░░   ░███ ░███ ███░░███  ░░░░███░███░░░  ▌
 * ▐ █████   █████░░██████  █████░███ █████░░██████  ████████ ░░████████ ██████ ░░██████ ▌
 * ▐░░░░░   ░░░░░  ░░░░░░  ░░░░░ ░░░ ░░░░░  ░░░░░░  ░░░░░░░░   ░░░░░░░░ ░░░░░░   ░░░░░░  ▌
 * ▐▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▌
 */