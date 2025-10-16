package tui.components

import tui.DebugLogger
import tui.ScreenObject
import java.io.File

class MarkdownParser(
    override var screenObject: ScreenObject,
    override var row: Int,
    override var col: Int?,
    val pathToMarkdown: String
) : Component {
    override var isDirty: Boolean = true
    override val area: Area = Area(row, col ?: 0, 1, screenObject.terminalWidth)
    override val centeredStartingPoint = (screenObject.terminalWidth / 2) - (screenObject.terminalHeight / 2)

    val components = mutableListOf<Component>()

    init {
        val lines = File(pathToMarkdown).readLines()
        val maxWidth =  lines.maxOf { it.length }

        val title = Regex("^#+ .*") // Match on all headers
        val horizontalLine = Regex("^-{3,}") // Match on all horizontal lines --- or ----------------------
        val openTodoBox = Regex("\\s*- \\[ ] .*")
        val closedTodoBox = Regex("^\\s*- \\[[xX]] .*")
        val text = Regex("^([a-zA-Z]*\\s*\\d*)*.*")

        row = 5
        val line = "-".repeat(screenObject.terminalWidth)

        lines.forEach { line ->
            when{
                line.matches(title) -> DebugLogger.log("Title")
                line.matches(title) -> DebugLogger.log("Title")
                line.matches(horizontalLine) -> DebugLogger.log("Horizontal line")
                line.matches(openTodoBox) -> DebugLogger.log("Open todo")
                line.matches(closedTodoBox) -> DebugLogger.log("Closed todo")
                line.matches(text) -> {
                    val match = text.find(line)
                    val full = match?.groupValues?.get(0)  // full match
                    DebugLogger.log("Text: $full")
                }
            }
        }
    }

    override fun render() {
        //TODO("Not yet implemented")
        return
    }

}