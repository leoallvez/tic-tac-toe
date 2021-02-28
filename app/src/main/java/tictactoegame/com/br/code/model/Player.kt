package tictactoegame.com.br.code.model

import tictactoegame.com.br.code.GetPositionFuction
import tictactoegame.com.br.code.Seed


abstract class Player(private val seed: Seed) {

    protected val board: Board = Board
    var turn: Boolean = false
    var points: Int = 0

    fun turnChange() {
        turn = turn.not()
    }

    abstract fun play(row: Int, column: Int)

    fun toScore() {
        points++
    }

    fun won():Boolean {
        val getPositionFuns = board.getPositionsFuns()
        getPositionFuns.forEach { getPosition ->
            val position: List<List<Cell>> = getPosition.invoke()
            position.forEach { cells: List<Cell> ->
                if(isFilledCells(cells)) {
                    return true
                }
            }
        }

        return false
    }

    private fun isFilledCells(cells: List<Cell>): Boolean {
        return cells.filter { it.content == seed }.size == Board.SIZE
    }
}
