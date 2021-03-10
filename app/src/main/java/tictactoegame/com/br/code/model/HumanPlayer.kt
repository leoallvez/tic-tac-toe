package tictactoegame.com.br.code.model

import tictactoegame.com.br.code.Seed

data class HumanPlayer(private val seed: Seed) : Player(seed) {

    fun play(row: Int, col: Int) {
        val cell = Board.cells[row][col]
        if (cell.isEmpty()) {
            cell.content = seed
        }
    }
}
