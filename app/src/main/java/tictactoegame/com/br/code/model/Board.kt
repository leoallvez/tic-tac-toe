package tictactoegame.com.br.code.model

import tictactoegame.com.br.code.Seed

class Board(private val playerOne :Player, private val playerTwo :Player) {

    var cells = Array(ROW) { row ->
        Array(COLUMN) { column ->
            Cell(row, column)
        }
    }

    fun start() {
        cells = Array(ROW) { row ->
            Array(ROW) { column ->
                Cell(row, column)
            }
        }
    }

    private fun isFullyPopulated(): Boolean {
        for(row in 0 until ROW) {
            for(column in 0 until COLUMN) {
                val isEmpty = cells[row][column].content == Seed.EMPTY
                if(isEmpty) {
                    return false
                }
            }
        }
        return true
    }

    fun gameOver() = isFullyPopulated().or(playerOne.won()).or(playerTwo.won())

    //fun showPosition(i: Int) = positions[i]

    fun isEmptyPosition(row: Int, column: Int): Boolean {
        return cells[row][column].content == Seed.EMPTY
    }

    companion object {
        const val ROW = 3
        const val COLUMN = 3
    }
}