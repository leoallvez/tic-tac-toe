package io.github.leoallvez.tictactoe.model

import io.github.leoallvez.tictactoe.Seed

class HumanPlayer(seed: Seed, board: Board = Board) : Player(seed, board) {

    fun play(row: Int, col: Int) {
        val cell = board.cells[row][col]
        if (cell.isEmpty()) {
            cell.content = seed
        }
    }
}
