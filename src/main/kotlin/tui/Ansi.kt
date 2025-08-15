package tui

object Ansi {
    object TextStyles {
        const val BOLD = "\u001B[1m"
        const val DIM = "\u001B[2m"
        const val ITALIC = "\u001B[3m"     // Not supported in all terminals
        const val UNDERLINE = "\u001B[4m"
        const val BLINK = "\u001B[5m"      // Rarely used
        const val REVERSE = "\u001B[7m"    // Swap fg/bg
        const val HIDDEN = "\u001B[8m"     // Invisible text
        const val RESET = "\u001B[0m"
    }
    object Cursor {
        fun moveCursor(row: Int, col: Int) = "\u001B[${row};${col}H"
        const val CURSOR_UP = "\u001B[1A"
        const val CURSOR_DOWN = "\u001B[1B"
        const val CURSOR_RIGHT = "\u001B[1C"
        const val CURSOR_LEFT = "\u001B[1D"
        const val SAVE_CURSOR = "\u001B[s"
        const val RESTORE_CURSOR = "\u001B[u"
        const val HIDE_CURSOR = "\u001B[?25l"
        const val SHOW_CURSOR = "\u001B[?25h"
    }
    object Screen {
        const val CLEAR_SCREEN = "\u001B[2J"
        const val CLEAR_LINE = "\u001B[2K"
        const val CLEAR_LINE_FROM_CURSOR = "\u001B[K"
        const val CLEAR_SCREEN_FROM_CURSOR = "\u001B[0J"
        const val CLEAR_SCREEN_TO_CURSOR = "\u001B[1J"
    }
    object Color {
        // Set foreground color; Set 8 bit color
        fun color(fg: Int) = "\u001B[38;5;${fg}m"
        object Foreground {
            const val BLACK = "\u001B[30m"
            const val RED = "\u001B[31m"
            const val GREEN = "\u001B[32m"
            const val YELLOW = "\u001B[33m"
            const val BLUE = "\u001B[34m"
            const val MAGENTA = "\u001B[35m"
            const val CYAN = "\u001B[36m"
            const val WHITE = "\u001B[37m"
            const val DEFAULT = "\u001B[39m"
            const val BRIGHT_BLACK = "\u001B[90m"
            const val BRIGHT_RED = "\u001B[91m"
            const val BRIGHT_GREEN = "\u001B[92m"
            const val BRIGHT_YELLOW = "\u001B[93m"
            const val BRIGHT_BLUE = "\u001B[94m"
            const val BRIGHT_MAGENTA = "\u001B[95m"
            const val BRIGHT_CYAN = "\u001B[96m"
            const val BRIGHT_WHITE = "\u001B[97m"
        }

        object Background {
            const val BLACK = "\u001B[40m"
            const val RED = "\u001B[41m"
            const val GREEN = "\u001B[42m"
            const val YELLOW = "\u001B[43m"
            const val BLUE = "\u001B[44m"
            const val MAGENTA = "\u001B[45m"
            const val CYAN = "\u001B[46m"
            const val WHITE = "\u001B[47m"
            const val DEFAULT = "\u001B[49m"
            const val BRIGHT_BLACK = "\u001B[100m"
            const val BRIGHT_RED = "\u001B[101m"
            const val BRIGHT_GREEN = "\u001B[102m"
            const val BRIGHT_YELLOW = "\u001B[103m"
            const val BRIGHT_BLUE = "\u001B[104m"
            const val BRIGHT_MAGENTA = "\u001B[105m"
            const val BRIGHT_CYAN = "\u001B[106m"
            const val BRIGHT_WHITE = "\u001B[107m"
        }

    }
}