package tictactoegame.com.br.code.model

import tictactoegame.com.br.code.Seed

data class HumanPlayer(private val seed: Seed) : Player(seed) {
    override fun play(row: Int, column: Int) {
        if (board.isEmptyPosition(row, column)) {
            board.cells[row][column].content = seed
        }
    }
}
