package io.github.leoallvez.tictactoe.model

import io.github.leoallvez.tictactoe.Seed

class Cell(val row: Int, val col: Int) {
    var content: Seed = Seed.EMPTY

    fun clear() {
        content = Seed.EMPTY
    }

    fun isEmpty(): Boolean {
        return content == Seed.EMPTY
    }

    fun isNotCenter(): Boolean {
        return !(row == 1 && col == 1)
    }
}
