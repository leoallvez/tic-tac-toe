package tictactoegame.com.br.code.model

import tictactoegame.com.br.code.Seed

class Cell(val row: Int, val col: Int) {
    var content: Seed = Seed.EMPTY
    fun clear() {
        content = Seed.EMPTY
    }
}
