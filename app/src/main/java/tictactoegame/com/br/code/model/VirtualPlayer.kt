package tictactoegame.com.br.code.model

import tictactoegame.com.br.code.Seed
import java.util.*

class VirtualPlayer(val seed: Seed) : Player(seed) {

    private val opponentSeed by lazy {
        if(seed == Seed.CROSS) Seed.NOUGHT else Seed.CROSS
    }

    override fun play(row: Int, col: Int) {
        board.cells[row][col].content = seed
    }

    fun randomPlay() {
        fun getNumber(): Int = Random().nextInt(Board.SIZE)
        board.cells[getNumber()][getNumber()].content = seed
    }


    fun play()  {
        val rules = listOf<() -> Boolean>(
            ::rule1, ::rule2, ::rule3, ::rule4, ::rule5
        )
        rules.forEach { rule ->
            rule.invoke()
        }
    }

    private fun getOpponentCells(cells: List<Cell>): List<Cell> {
        return cells.filter { it.content == opponentSeed }
    }

    private fun getEmptyCell(cells: List<Cell>): Cell? {
        return cells.find { it.content == Seed.EMPTY }
    }

    // 1 - se o oponente ocupar duas casas seguidas, ocupe a terceira
    private fun rule1(): Boolean = board.run { cells ->
        val opponentCells = getOpponentCells(cells)
        if(opponentCells.size == 2) {
            val cell = getEmptyCell(cells)
            cell?.let {
                board.cells[it.row][it.col].content = seed
                return@run true
            }
        }
        return@run false
    }

    // 2 - caso contrário se algum movimento que cria uma linhas com
    // duas casas ocupadas, faça ele.
    private fun rule2() : Boolean = board.run { cells ->
        val myCells = cells.filter { it.content == seed }
        if(myCells.size == 2) {
            val cell = getEmptyCell(cells)
            cell?.let {
                board.cells[it.row][it.col].content = seed
                return@run true
            }
        }
        return@run false
    }

    // 3 - caso contrário, se o espaço do centro estive vazio, ocupe ele
    private fun rule3(): Boolean{
        if (board.cells[1][1].isEmpty()) {
            board.cells[1][1].content = seed
            return true
        }
        return false
    }

    // 4 - caso contrário, se o oponente preencheu uma quina, preencha a
    // quina contrária.
    private fun rule4(): Boolean {
        val diagonalCells = board.getDiagonalsList()
        diagonalCells.forEach { cells ->
            cells.forEach { cell ->
                val oppCorner: Cell? = board.getOppositeCornerCell(cell)
                oppCorner?.let {
                    if(oppCorner.isEmpty()) {
                        board.cells[oppCorner.row][oppCorner.col].content = seed
                        return true
                    }
                }
            }
        }

        return false
    }

    // 5 - caso contrário, se tiver uma quina vázia preencha ela.
    private fun rule5(): Boolean {
        val diagonals = board.getDiagonalsList()
        diagonals.forEach { cells ->
            cells.forEach { cell ->
                if(cell.isEmpty() && cell.isNotCenter()) {
                    board.cells[cell.row][cell.col].content = seed
                    return true
                }
            }
        }
        return false
    }
    // 6 - se nenhuma dessas condições acontencer, pode preencher qualquer
    // espaço vázio.
}
