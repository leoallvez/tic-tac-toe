package io.github.leoallvez.tictactoe.model

import io.github.leoallvez.tictactoe.Seed
import java.util.*

class VirtualPlayer(private val seed: Seed) : Player(seed) {

    private val opponentSeed by lazy { if(seed == Seed.X) Seed.O else Seed.X }

    private val rules by lazy {
        listOf<() -> Boolean> (::rule1, ::rule2, ::rule3, ::rule4, ::rule5, ::rule6)
    }

    fun randomPlay(): Boolean {
        val ckeckedCells = mutableSetOf<Cell>()
        fun getNumber(): Int = Random().nextInt(Board.SIZE)
        do {
            val cell = board.cells[getNumber()][getNumber()]
            ckeckedCells.add(cell)
            if(cell.isEmpty()) {
                cell.content = seed
                return true
            }
        } while (ckeckedCells.size != 9)
        return false
    }

    fun play(): Boolean {
        rules.forEach { rule ->
            if(rule.invoke()) {
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

    // 2 - caso contrário se algum movimento que cria uma linhas com
    // duas casas ocupadas, faça ele.
    private fun rule1(): Boolean = rule0(seed)

    // 1 - se o oponente ocupar duas casas seguidas, ocupe a terceira
    private fun rule2(): Boolean = rule0(opponentSeed)

    // 3 - caso contrário, se o espaço do centro estive vazio, ocupe ele
    private fun rule3(): Boolean {
        val cell = board.cells[1][1]
        if (cell.isEmpty()) {
            cell.content = seed
            return true
        }
        return false
    }

    // 4 - caso contrário, se o oponente preencheu uma quina, preencha a quina contrária.
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

    // 5 - caso contrário, se tiver uma quina vázia preencha ela.
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

    // 6 - se nenhuma dessas condições acontencer, pode preencher qualquer espaço vázio.
    private fun rule6(): Boolean = randomPlay()
}
