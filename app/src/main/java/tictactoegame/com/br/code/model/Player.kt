package tictactoegame.com.br.code.model

import tictactoegame.com.br.code.Seed


abstract class Player(private val seed: Seed) {

    protected val board: Board = Board

    var points: Int = 0

    fun toScore() {
        points++
    }

    fun won(): Boolean {
        val getPositionFuns = board.getPositionsFuns()
        getPositionFuns.forEach { getPosition ->
            val position: List<List<Cell>> = getPosition.invoke()
            position.forEach { cells: List<Cell> ->
                val isFilledCells = cells.filter { it.content == seed }.size == Board.SIZE
                if(isFilledCells) {
                    return@won true
                }
            }
        }
        return false
    }
}
