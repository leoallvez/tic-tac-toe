package tictactoegame.com.br.code.model

import tictactoegame.com.br.code.Seed
import tictactoegame.com.br.code.model.Board.COLUMN
import tictactoegame.com.br.code.model.Board.ROW
import tictactoegame.com.br.code.model.Board.TABLE_LENGHT

open class Player(val seed: Seed) {

    private val board: Board = Board

    var turn: Boolean = false
    var points: Int = 0

    fun turnChange() {
        turn = turn.not()
    }

    fun play(row: Int, column: Int) {
        if (board.isEmptyPosition(row, column)) {
            board.cells[row][column].content = seed
        }
    }

    fun toScore() {
        points++
    }

    fun won() = wonOnHorizontal() || wonOnVertical() || wonOnDiagonal()

    private fun wonOnHorizontal(): Boolean {
        for(row in 0 until ROW) {
            val rowCells = board.cells[row].toList()
            if(isFilledCells(rowCells)) {
                return true
            }
        }
        return false
    }

    private fun wonOnVertical(): Boolean {
        for(col in 0 until COLUMN) {
            val columnCells = mutableListOf<Cell>()
            for(row in 0 until ROW) {
                val cell = board.cells[row][col]
                columnCells.add(cell)
            }
            if (isFilledCells(columnCells)) {
                return true
            }
        }
        return false
    }

    private fun wonOnDiagonal(): Boolean {

        val cells = mutableListOf<Cell>()

        for(i in 0 until TABLE_LENGHT) {
            cells.add(board.cells[i][i])
        }

        if(isFilledCells(cells)) {
            return true
        }

        var col = 2
        cells.clear()
        for(row in 0 until ROW) {
            cells.add(board.cells[row][col--])
        }

        if(isFilledCells(cells)) {
            return true
        }

        return false
    }

    private fun isFilledCells(cells: List<Cell>): Boolean {
        return cells.filter { it.content == seed }.toMutableList().size == TABLE_LENGHT
    }
}
