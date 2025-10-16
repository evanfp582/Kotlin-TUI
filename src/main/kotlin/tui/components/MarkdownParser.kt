package tui.components

import tui.Ansi
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

        val title = Regex("^#+ (.*)") // Match on all headers
        val horizontalLine = Regex("^-{3,}") // Match on all horizontal lines --- or ----------------------
        val openTodoBox = Regex("\\s*- \\[ ] .*")
        val closedTodoBox = Regex("^\\s*- \\[[xX]] .*")
        val text = Regex("^([a-zA-Z]*\\s*\\d*)*.*")

        row = 2

        lines.forEach { line ->
            when{
                line.matches(title) -> {
                    val match = title.find(line)
                    val text = match?.groupValues?.get(1)
                    DebugLogger.log("Title")
                    components.add(Title(screenObject, row, 0, text?: "", Ansi.TextStyles.BOLD))
                }
                line.matches(horizontalLine) -> {
                    DebugLogger.log("Horizontal line")
                    components.add(Text(screenObject, row, 0, "-".repeat(screenObject.terminalWidth-1)))
                }
                line.matches(openTodoBox) -> {
                    val match = openTodoBox.find(line)
                    val full = match?.groupValues?.get(0)
                    DebugLogger.log("Open todo")
                    components.add(Text(screenObject, row, 0, full ?: ""))
                }
                line.matches(closedTodoBox) -> {
                    val match = closedTodoBox.find(line)
                    val full = match?.groupValues?.get(0)
                    DebugLogger.log("Closed todo")
                    components.add(Text(screenObject, row, 0, full ?: ""))
                }
                line.matches(text) -> {
                    val match = text.find(line)
                    val full = match?.groupValues?.get(0)  // full match
                    DebugLogger.log("Text: $full")
                    components.add(Text(screenObject, row, 0, full ?: ""))
                }
                else -> {
                    DebugLogger.log("ELSE: $line")
                    components.add(Text(screenObject, row, 0, line))
                }
            }
            row += 1
        }
    }

    override fun render() {
        if (isDirty) {
            components.forEach { it.render() }
        }
        isDirty = false
    }
}