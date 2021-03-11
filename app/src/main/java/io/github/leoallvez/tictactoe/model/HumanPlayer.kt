package io.github.leoallvez.tictactoe.model

import io.github.leoallvez.tictactoe.Seed

data class HumanPlayer(private val seed: Seed) : Player(seed) {

    fun play(row: Int, col: Int) {
        val cell = Board.cells[row][col]
        if (cell.isEmpty()) {
            cell.content = seed
        }
    }
}
