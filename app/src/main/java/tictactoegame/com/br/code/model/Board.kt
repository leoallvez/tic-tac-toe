package tictactoegame.com.br.code.model

import tictactoegame.com.br.code.model.Board.*

/*
 Created by Leonardo on 25/01/2019.
*/

class Board(private val playerOne :Player, private val playerTwo :Player) {

    init{start()}

    fun start() {
        positions = intArrayOf(3, 2, 3, 2, 4, 2, 3, 2, 3)
    }

    private fun isFullyPopulated(): Boolean {
        var count = 0
        for (position in positions) {
            if (position.equals(playerOne.tag).or(position.equals(playerTwo.tag))) {
                count++
            }
        }
        return count.equals(positions.size)
    }

    fun gameOver()= isFullyPopulated().or(playerOne.won()).or(playerTwo.won())

    fun showPosition(i: Int)= positions[i]

    companion object {
        var positions = IntArray(9)
        private const val minimum = 2
        fun isEmptyPosition(i: Int) = positions[i] >= minimum
    }
}