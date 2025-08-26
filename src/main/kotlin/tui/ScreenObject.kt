package tui

class ScreenObject(val rows: Int, val cols: Int) {
    private val buffer: Array<CharArray> = Array(rows) { CharArray(cols) {' '} }

    private val diffArray: Array<Boolean> = Array(rows) {false}

    fun clear() {
        for (row in 0..rows){
            for (col in 0..cols){
                buffer[row][col] = ' '
            }
            diffArray[row] = true
        }
    }

    fun clear_row(row: Int){
        for (i in 0..<cols){
            buffer[row][i] = ' '
        }
    }

    fun setChar(row: Int, col: Int, char: Char){
        buffer[row][col] = char
    }

    fun setString(row: Int, col: Int, string: String){
        diffArray[row] = true
        clear_row(row)
        for ((index, char) in string.withIndex()){
            buffer[row][col+index] = char
        }
    }

    fun render() {
        for ((index, bool) in diffArray.withIndex()){
            if (bool) {
                val changes =  buffer[index].joinToString("")
                Terminal.printAt(index, 0, changes.padEnd(cols-buffer[index].joinToString("").length, '*'))
            }
        }
    }

}