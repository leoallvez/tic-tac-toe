package tictactoegame.com.br.code.model

import tictactoegame.com.br.code.Seed

object Board {

    var cells = startCells()
    private set

    private fun startCells() = Array(ROW) { row ->
        Array(COLUMN) { column ->
            Cell(row, column)
        }
    }

    fun start() {
        cells = startCells()
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

    //fun gameOver() = isFullyPopulated().or(playerOne.won()).or(playerTwo.won())

    //fun showPosition(i: Int) = positions[i]

    fun isEmptyPosition(row: Int, column: Int): Boolean {
        return cells[row][column].content == Seed.EMPTY
    }

    //companion object {
        const val TABLE_LENGHT = 3
        const val ROW = TABLE_LENGHT
        const val COLUMN = TABLE_LENGHT
    //}
}
