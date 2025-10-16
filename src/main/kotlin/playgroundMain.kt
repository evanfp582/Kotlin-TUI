
import kotlinx.coroutines.*
import tui.Ansi
import tui.ScreenObject
import tui.UIManager
import tui.components.Button
import tui.components.MarkdownParser
import tui.components.Select
import tui.components.Text
import tui.components.TextBox
import tui.components.Title
import tui.components.ToggleButton

fun main(): Unit = runBlocking {

    val title="""
+------+
|Button|
+------+""".trimIndent()

    val ui = UIManager()
//    val screenRouter: Map<String, Int> = mapOf(
//        "Programs" to 1,
//        "Test Page" to 2,
//        "README" to 3
//    )

    val secondaryButton = """
|~~~~~~~~~~~~~~|
|  Odd Button  |
|~~~~~~~~~~~~~~|
    """.trimIndent()

    // Screen 1 Title Screen
    ui.addScreen(
        ScreenObject().apply {
            addComponents(
                arrayOf(
                    MarkdownParser(this, 0,0, "C:\\Users\\evanf\\Documents\\projects\\Kotlin-TUI\\small_test_README.md")
                )
            )
        }
    )

    ui.run()

}