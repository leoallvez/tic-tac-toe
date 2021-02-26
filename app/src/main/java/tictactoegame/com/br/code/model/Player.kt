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
        var rowCells: List<Cell>
        for(row in 0 until ROW) {
            rowCells = board.cells[row].toList().filter { it.content == seed }
            if(rowCells.size == ROW) {
                return true
            }
        }
        return false
    }

    private fun wonOnVertical(): Boolean {
        for(col in 0 until COLUMN) {
            var columnCells = mutableListOf<Cell>()
            for(row in 0 until ROW) {
                val cell = board.cells[row][col]
                columnCells.add(cell)
            }
            columnCells = columnCells.filter { it.content == seed }.toMutableList()
            if (columnCells.size == COLUMN) {
                return true
            }
        }
        return false
    }

    private fun wonOnDiagonal(): Boolean {
        var count = 0

        for(i in 0 until TABLE_LENGHT) {
            val hasTag = board.cells[i][i].content == seed
            if(hasTag) count++
        }

        if(count == TABLE_LENGHT) {
            return true
        }

        count = 0
        for(row in 0 until ROW) {
            for(col in COLUMN downTo 0) {
                val hasTag = board.cells[row][col].content == seed
                if(hasTag) count++
            }
        }

        if(count == TABLE_LENGHT) {
            return true
        }

        return false
    }
}
