package io.github.leoallvez.tictactoe.model

import io.github.leoallvez.tictactoe.Seed
import java.util.*

class VirtualPlayer(seed: Seed, board: Board = Board) : Player(seed, board) {

    val opponentSeed by lazy { if(seed == Seed.X) Seed.O else Seed.X }

    //TODO: make a game level
    private val rules by lazy {
        listOf<() -> Boolean> (::rule1, ::rule2, ::rule3, ::rule4, ::rule5, ::rule6)
    }

    fun randomPlay(): Boolean {
        val checkedCells = mutableSetOf<Cell>()
        fun getNumber(): Int = Random().nextInt(Board.SIZE)
        do {
            val cell = board.cells[getNumber()][getNumber()]
            checkedCells.add(cell)
            if(cell.isEmpty()) {
                cell.content = seed
                return true
            }
        } while (checkedCells.size <= 8)
        return false
    }

    fun play(): Boolean {
        rules.forEach { rule ->
            if(rule.invoke()) {
                if (won()) { toScore() }
                return@play true
            }
        }
        return false
    }

    private fun rule0(_seed: Seed): Boolean {
        var play = false
        board.run { cells ->
            if(play.not()) {
                val seedCells = cells.filter { it.content == _seed }
                if (seedCells.size == 2) {
                    val cell = cells.find { it.isEmpty() }
                    cell?.let {
                        cell.content = seed
                        play = true
                    }
                }
            }
        }
        return play
    }

    /**
     * 1 - If any movement creates a line with two occupied cells, do it.
     * @return [Boolean] applied rule or not
     */
    private fun rule1(): Boolean = rule0(seed)

    /**
     * 2 - If the opponent occupies two cells in a row, occupy the third.
     * @return [Boolean] applied rule or not
     */
    private fun rule2(): Boolean = rule0(opponentSeed)

    /**
     * 3 - Otherwise, if the central space is empty, occupy it.
     * @return [Boolean] applied rule or not
     */
    private fun rule3(): Boolean {
        val cell = board.cells[1][1]
        if (cell.isEmpty()) {
            cell.content = seed
            return true
        }
        return false
    }

    /**
     * 4 - Otherwise, if the opponent has filled in a corner, fill in the opposite corner.
     * @return [Boolean] applied rule or not
     */
    private fun rule4(): Boolean {
        val diagonalCells = board.getDiagList()
        diagonalCells.forEach { cells ->
            cells.forEach { cell ->
                val oppCorner: Cell? = board.getOppositeCornerCell(cell)
                oppCorner?.let {
                    if(oppCorner.isEmpty()) {
                        oppCorner.content = seed
                        return true
                    }
                }
            }
        }
        return false
    }

    /**
     * 5 - Otherwise, if you have an empty corner, fill it out.
     * @return [Boolean] applied rule or not
     */
    private fun rule5(): Boolean {
        val diagonals = board.getDiagList()
        diagonals.forEach { cells ->
            cells.forEach { cell ->
                if(cell.isEmpty() && cell.isNotCenter()) {
                    cell.content = seed
                    return true
                }
            }
        }
        return false
    }

    /**
     * 6 - if none of these conditions happens, you can fill any empty cell.
     * @return [Boolean] applied rule or not
     */
    private fun rule6(): Boolean = randomPlay()
}
