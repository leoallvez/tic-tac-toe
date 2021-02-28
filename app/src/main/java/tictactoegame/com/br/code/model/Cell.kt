package tictactoegame.com.br.code.model

import tictactoegame.com.br.code.Seed

class Cell(val row: Int, val col: Int) {
    var content: Seed = Seed.EMPTY

    fun clear() {
        content = Seed.EMPTY
    }

    fun isEmpty(): Boolean {
        return content == Seed.EMPTY
    }

    fun isNotCenter(): Boolean {
        return !(row == 0 && col == 0)
    }
}
