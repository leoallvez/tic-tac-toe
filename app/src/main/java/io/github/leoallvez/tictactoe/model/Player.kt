package io.github.leoallvez.tictactoe.model

import io.github.leoallvez.tictactoe.Seed

abstract class Player(private val seed: Seed) {

    protected val board: Board = Board

    var points: Int = 0
        private set

    fun toScore() {
        points++
    }

    fun won(): Boolean {
        var won = false
        board.run { cells ->
            if(won.not()) {
                val isFilledCells = cells.filter { it.content == seed }.size == Board.SIZE
                if(isFilledCells) {
                    won = true
                }
            }
        }
        return won
    }
}
