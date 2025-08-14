package tui

object Ansi {
    const val RESET = "\u001B[0m"
    const val CLEAR = "\u001B[2J"
    const val HIDE_CURSOR = "\u001B[?25l"
    const val SHOW_CURSOR = "\u001B[?25h"

    fun moveTo(row: Int, col: Int) = "\u001B[${row};${col}H"
    fun color(fg: Int) = "\u001B[38;5;${fg}m"
}

object Terminal {
    fun clear() {
        print(Ansi.CLEAR + Ansi.moveTo(1, 1))
    }

    fun printAt(row: Int, col: Int, text: String, color: Int? = null) {
        val coloredText = if (color != null) Ansi.color(color) + text + Ansi.RESET else text
        print(Ansi.moveTo(row, col) + coloredText)
    }

    fun hideCursor() = print(Ansi.HIDE_CURSOR)
    fun showCursor() = print(Ansi.SHOW_CURSOR)
}