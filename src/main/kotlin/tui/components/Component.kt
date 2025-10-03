package tui.components

import tui.ScreenObject

interface Component: Comparable<Component> {
    var screenObject: ScreenObject
    var row: Int
    var col: Int?
    val area: Area
    val centeredStartingPoint: Int
    var isDirty: Boolean
    fun render()
    fun forceRerender() {
        isDirty = true
        render()
        isDirty = false
    }
    fun validateSize() {
        if (area.width > screenObject.terminalWidth){
            throw IllegalArgumentException(
                "Title with width (${area.width}) exceeds terminal width of (${screenObject.terminalWidth})"
            )
        }
        if (area.height > screenObject.terminalHeight){
            throw IllegalArgumentException(
                "Title with width (${area.height}) exceeds terminal width of (${screenObject.terminalHeight})"
            )
        }
    }

    override fun compareTo(other: Component): Int {
        // First compare by row
        val rowComparison = row.compareTo(other.row)
        if (rowComparison != 0) {
            return rowComparison
        }
        // If rows are equal, compare by col
        val thisCol = col ?: (screenObject.terminalWidth / 2)
        val otherCol = other.col ?: (screenObject.terminalWidth / 2)
        return thisCol.compareTo(otherCol)
    }

}
data class Area(val row: Int, val col: Int, val height: Int, val width: Int)