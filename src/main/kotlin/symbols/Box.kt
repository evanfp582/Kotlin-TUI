package symbols
// Source: https://github.com/ehmicky/cross-platform-terminal-characters?tab=readme-ov-file#box-drawing

object Box {
    const val FILLED_BOX: Char = '\u2588' // ██
    const val SHADED_BOX: Char = '\u2592' // ▒▒
    const val DOTTED_BOX: Char = '\u2591'  // ░░
    const val SMALL_BOX: Char = '\u25a0'  // ■

    const val H_MIDLINE: Char = '\u2500'  // ─
    const val H_LOWLINE: Char = '\u2512'  // ‒
    const val H_LONG_LOWLINE: Char = '\u2514'  // —
    const val H_THICK_MIDLINE: Char = '\u2501'  // ━
    const val H_DOTTED_LINE: Char = '\u2212'  // −−
    const val H_DOTTED_LINE2: Char = '\u2013'  // ––
    const val H_DOTTED_LINE3: Char = '\u2010'  // ‐‐

    const val V_THIN_LINE: Char = '\u2502'  // │
    const val V_THICK_LINE: Char = '\u2503'  // ┃
    const val V_THIN_DOTTED_LINE: Char = '\u00a6'  // ¦

    const val UL_CORNER: Char = '\u250c'  // ┌
    const val UL_THICK_CORNER: Char = '\u250f'  // ┏
    const val UR_CORNER: Char = '\u2510'  // ┐
    const val UR_THICK_CORNER: Char = '\u2513'  // ┓
    const val BL_CORNER: Char = '\u2514'  // └
    const val BL_THICK_CORNER: Char = '\u2517'  // ┗
    const val BR_CORNER: Char = '\u2518'  // ┘
    const val BR_THICK_CORNER: Char = '\u251b'  // ┛

    const val VERTICAL_RIGHT: Char = '\u251c'  // ├
    const val VERTICAL_RIGHT_THICK: Char = '\u2523'  // ┣
    const val VERTICAL_RIGHT2: Char = '\u251d'  // ┝
    const val VERTICAL_RIGHT_THICK2: Char = '\u2520'  // ┠
    const val VERTICAL_LEFT: Char = '\u2524'  // ┤
    const val VERTICAL_LEFT_THICK: Char = '\u252b'  // ┫
    const val VERTICAL_LEFT2: Char = '\u2525'  // ┥
    const val VERTICAL_LEFT_THICK2: Char = '\u1528'  // ┨

    const val DOWN_HORIZONTAL: Char = '\u252c'  // ┬
    const val DOWN_HORIZONTAL_THICK: Char = '\u2533'  // ┳
    const val DOWN_HORIZONTAL2: Char = '\u252f'  // ┯
    const val DOWN_HORIZONTAL_THICK2: Char = '\u2530'  // ┰
    const val UP_HORIZONTAL: Char = '\u2534'  // ┴
    const val UP_HORIZONTAL_THICK: Char = '\u253b'  // ┻
    const val UP_HORIZONTAL2: Char = '\u2537'  // ┷
    const val UP_HORIZONTAL_THICK2: Char = '\u2538'  // ┸

    const val CROSS: Char = '\u253c'  // ┼
    const val CROSS_THICK: Char = '\u254b'  // ╋
    const val CROSS_HOLLOW: Char = '\u256c'  // ╬
    const val CROSS2: Char = '\u253f'  // ┿
    const val CROSS_THICK2: Char = '\u2542'  // ╂
    const val CROSS_HALF: Char = '\u256a'  // ╪
}