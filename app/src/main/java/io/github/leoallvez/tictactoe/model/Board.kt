package io.github.leoallvez.tictactoe.model

import io.github.leoallvez.tictactoe.GetPositionFunction

object Board {

    internal const val SIZE = 3

    val cells: Array<Array<Cell>> by lazy {
        Array(SIZE) { row ->
            Array(SIZE) { col ->
                Cell(row, col)
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

    fun getDiagList(): List<List<Cell>> {
        val diagonalsList = mutableListOf<List<Cell>>()
        val cellsList = mutableListOf<Cell>()

        for(i in 0 until SIZE) {
            cellsList.add(cells[i][i])
        }
        diagonalsList.add(cellsList.toCollection(mutableListOf()))
        var col = 2
        cellsList.clear()
        for(row in 0 until SIZE) {
            cellsList.add(cells[row][col--])
        }
        diagonalsList.add(cellsList)
        return diagonalsList
    }

    private fun getPositionsFunc(): List<GetPositionFunction> {
        return listOf<GetPositionFunction>(
            ::getRowsList, ::getColsList, ::getDiagList
        )
    }

    fun run(action: (List<Cell>) -> Unit) {
        val getPositionFunc = getPositionsFunc()
        getPositionFunc.forEach { getPosition ->
            val position: List<List<Cell>> = getPosition.invoke()
            position.forEach { cells: List<Cell> ->
                action(cells)
            }
        }
    }

    fun <T> run(action: (row: Int,col: Int) -> T) {
        for(row in 0 until SIZE) {
            for(col in 0 until SIZE) {
                action(row, col)
            }
        }
    }
}
