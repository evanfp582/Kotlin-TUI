package tui

object Terminal {
    fun clear() { print(Ansi.Screen.CLEAR_SCREEN + Ansi.Cursor.moveCursor(1, 1)) }
    fun clearRow(row: Int) { print(Ansi.Cursor.moveCursor(row, 0) + Ansi.Screen.CLEAR_LINE) }

    fun moveCursor(row: Int, col: Int){ print(Ansi.Cursor.moveCursor(row, col)) }

    fun printAt(row: Int, col: Int, text: String, color: Int? = null, style: String? = null) {
        val colorCode = color?.let { Ansi.Color.color(it) } ?: ""
        val styleCode = style ?: ""
        val formattedText = "$colorCode$styleCode$text${Ansi.TextStyles.RESET}"
        print(Ansi.Cursor.moveCursor(row, col) + formattedText)
    }
    fun formatText(text: String, color:Int? = null, style: String? = null): String {
        val colorCode = color?.let { Ansi.Color.color(it) } ?: ""
        val styleCode = style ?: ""
        return "$colorCode$styleCode$text${Ansi.TextStyles.RESET}"
    }

    fun testPage(){
        /**
         * Print out all the possibilities with ANSI
         */
        // 1. Text Styles
        println("=== Text Styles ===")
        println("${Ansi.TextStyles.BOLD}BOLD${Ansi.TextStyles.RESET}")
        println("${Ansi.TextStyles.DIM}DIM${Ansi.TextStyles.RESET}")
        println("${Ansi.TextStyles.ITALIC}ITALIC (may not work)${Ansi.TextStyles.RESET}")
        println("${Ansi.TextStyles.UNDERLINE}UNDERLINE${Ansi.TextStyles.RESET}")
        println("${Ansi.TextStyles.BLINK}BLINK (rare)${Ansi.TextStyles.RESET}")
        println("${Ansi.TextStyles.REVERSE}REVERSE FG/BG${Ansi.TextStyles.RESET}")
        println("${Ansi.TextStyles.HIDDEN}HIDDEN (invisible)${Ansi.TextStyles.RESET}")
        println()

        // 2. Foreground colors
        println("=== Foreground Colors ===")
        with(Ansi.Color.Foreground) {
            println("$BLACK BLACK ${Ansi.TextStyles.RESET}")
            println("$RED RED ${Ansi.TextStyles.RESET}")
            println("$GREEN GREEN ${Ansi.TextStyles.RESET}")
            println("$YELLOW YELLOW ${Ansi.TextStyles.RESET}")
            println("$BLUE BLUE ${Ansi.TextStyles.RESET}")
            println("$MAGENTA MAGENTA ${Ansi.TextStyles.RESET}")
            println("$CYAN CYAN ${Ansi.TextStyles.RESET}")
            println("$WHITE WHITE ${Ansi.TextStyles.RESET}")
            println("$DEFAULT DEFAULT ${Ansi.TextStyles.RESET}")
            println("$BRIGHT_BLACK BRIGHT_BLACK ${Ansi.TextStyles.RESET}")
            println("$BRIGHT_RED BRIGHT_RED ${Ansi.TextStyles.RESET}")
            println("$BRIGHT_GREEN BRIGHT_GREEN ${Ansi.TextStyles.RESET}")
            println("$BRIGHT_YELLOW BRIGHT_YELLOW ${Ansi.TextStyles.RESET}")
            println("$BRIGHT_BLUE BRIGHT_BLUE ${Ansi.TextStyles.RESET}")
            println("$BRIGHT_MAGENTA BRIGHT_MAGENTA ${Ansi.TextStyles.RESET}")
            println("$BRIGHT_CYAN BRIGHT_CYAN ${Ansi.TextStyles.RESET}")
            println("$BRIGHT_WHITE BRIGHT_WHITE ${Ansi.TextStyles.RESET}")
        }
        println()

        // 3. Background colors
        println("=== Background Colors ===")
        with(Ansi.Color.Background) {
            println("$BLACK BLACK ${Ansi.TextStyles.RESET}")
            println("$RED RED ${Ansi.TextStyles.RESET}")
            println("$GREEN GREEN ${Ansi.TextStyles.RESET}")
            println("$YELLOW YELLOW ${Ansi.TextStyles.RESET}")
            println("$BLUE BLUE ${Ansi.TextStyles.RESET}")
            println("$MAGENTA MAGENTA ${Ansi.TextStyles.RESET}")
            println("$CYAN CYAN ${Ansi.TextStyles.RESET}")
            println("$WHITE WHITE ${Ansi.TextStyles.RESET}")
            println("$DEFAULT DEFAULT ${Ansi.TextStyles.RESET}")
            println("$BRIGHT_BLACK BRIGHT_BLACK ${Ansi.TextStyles.RESET}")
            println("$BRIGHT_RED BRIGHT_RED ${Ansi.TextStyles.RESET}")
            println("$BRIGHT_GREEN BRIGHT_GREEN ${Ansi.TextStyles.RESET}")
            println("$BRIGHT_YELLOW BRIGHT_YELLOW ${Ansi.TextStyles.RESET}")
            println("$BRIGHT_BLUE BRIGHT_BLUE ${Ansi.TextStyles.RESET}")
            println("$BRIGHT_MAGENTA BRIGHT_MAGENTA ${Ansi.TextStyles.RESET}")
            println("$BRIGHT_CYAN BRIGHT_CYAN ${Ansi.TextStyles.RESET}")
            println("$BRIGHT_WHITE BRIGHT_WHITE ${Ansi.TextStyles.RESET}")
        }

//        // 4. Optional: 8-bit foreground demo
//        println("\n=== 8-bit Foreground Colors (0–255) ===")
//        for (i in 0..255) {
//            print("${Ansi.Color.color(i)}%3d ".format(i))
//            if ((i + 1) % 16 == 0) println(Ansi.TextStyles.RESET)
//        }
//        println(Ansi.TextStyles.RESET)
//
//        // 5. Optional: 8-bit background demo
//        println("\n=== 8-bit Background Colors (0–255) ===")
//        for (i in 0..255) {
//            print("\u001B[48;5;${i}m%3d ".format(i))
//            if ((i + 1) % 16 == 0) println(Ansi.TextStyles.RESET)
//        }
//        println(Ansi.TextStyles.RESET)

    }

    fun hideCursor() = print(Ansi.Cursor.HIDE_CURSOR)
    fun showCursor() = print(Ansi.Cursor.SHOW_CURSOR)
}