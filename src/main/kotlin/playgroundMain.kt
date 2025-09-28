
import kotlinx.coroutines.*
import tui.Ansi
import tui.ScreenObject
import tui.UIManager
import tui.components.Select
import tui.components.Text
import tui.components.Title

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

    // Screen 1 Title Screen
    ui.addScreen(
        ScreenObject().apply {
            addComponents(
                arrayOf(
                    Title(this, 3, null, title),
                )
            )
        }
    )

    ui.run()

}