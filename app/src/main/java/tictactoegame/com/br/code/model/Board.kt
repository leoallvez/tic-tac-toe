package tictactoegame.com.br.code.model

import tictactoegame.com.br.code.GetPositionFuction
import tictactoegame.com.br.code.Seed

object Board {

    internal const val SIZE = 3

    val cells: Array<Array<Cell>> by lazy {
        Array(SIZE) { row ->
            Array(SIZE) { column ->
                Cell(row, column)
            }
        }
    }

    fun restart() = run { row, col ->
        cells[row][col].clear()
    }

    fun getOppositeCornerCell(cell: Cell): Cell? {

        fun Int.opp(): Int = if(this == 0) 2 else 0

        val positions = listOf(Pair(0, 0), Pair(2, 2), Pair(0, 2), Pair(2, 0))
        positions.forEach { p ->
            if(cell.row == p.first && cell.col == p.second) {
                return cells[p.first.opp()][p.second.opp()]
            }
        }
        return null
    }

    private fun getRowsList(): List<List<Cell>> {
        val rowsList = mutableListOf<List<Cell>>()
        for(row in 0 until SIZE) {
            val rowCells = cells[row].toList()
            rowsList.add(rowCells)
        }
        return rowsList
    }

    private fun getColsList(): List<List<Cell>> {
        val colsList = mutableListOf<List<Cell>>()
        for(col in 0 until SIZE) {
            val colCells = mutableListOf<Cell>()
            for(row in 0 until SIZE) {
                val cell = cells[row][col]
                colCells.add(cell)
            }
            colsList.add(colCells)
        }
        return colsList
    }

    fun getDiagonalsList(): List<List<Cell>> {
        val diagonalsList = mutableListOf<List<Cell>>()
        val cellsList = mutableListOf<Cell>()

        for(i in 0 until SIZE) {
            cellsList.add(cells[i][i])
        }
        diagonalsList.add(cellsList)
        var col = 2
        cellsList.clear()
        for(row in 0 until SIZE) {
            cellsList.add(cells[row][col--])
        }
        return diagonalsList
    }

    fun getPositionsFuns(): List<GetPositionFuction> {
        return listOf<GetPositionFuction>(
            ::getRowsList, ::getColsList, ::getDiagonalsList
        )
    }

    fun run(action: (cells: List<Cell>) -> Boolean): Boolean {
        val getPositionFuns = getPositionsFuns()
        getPositionFuns.forEach { getPosition ->
            val position: List<List<Cell>> = getPosition.invoke()
            position.forEach { cells: List<Cell> ->
                return action(cells)
            }
        }
        return false
    }

    fun <T> run(action: (row: Int,col: Int) -> T) {
        for(row in 0 until SIZE) {
            for(col in 0 until SIZE) {
                action(row, col)
            }
        }
    }

    fun <T> runWithBack(action: (row: Int,col: Int) -> T): T? {
        for(row in 0 until SIZE) {
            for(col in 0 until SIZE) {
                return action(row, col)
            }
        }
        return null
    }

    private fun isFullyPopulated(): Boolean = runWithBack run@{ row, col ->
        if (isEmptyPosition(row, col)) {
            return@run true
        }
        return@run false
    } ?: false

    fun isEmptyPosition(row: Int, col: Int): Boolean {
        return cells[row][col].content == Seed.EMPTY
    }
}
