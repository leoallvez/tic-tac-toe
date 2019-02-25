package tictactoegame.com.br.code.model

import java.util.*

/*
 * Created by Leonardo on 25/01/2019.
 */

class PlayerVirtual(name: String, tag: Int) : Player(name, tag) {

    init {
        turn = false
    }

    private fun play() {

        var higherNumber = 0
        var higherNumberPosition = 0

        for (i in 0 until Board.positions.size) {
            if (Board.positions[i] > higherNumber && Board.isEmptyPosition(i)) {
                higherNumber = Board.positions[i]
                higherNumberPosition = i
            }
        }
        Board.positions[higherNumberPosition] = tag
    }

    fun randomPlay() {
        Board.positions[Random().nextInt(Board.positions.size)] = tag
    }

    private fun canWin(tag: Int): Boolean {

        val positions = Board.positions

        //Horizontal Superior
        if (positions[0] == tag && positions[1] == tag && Board.isEmptyPosition(2)) {
            positions[2] = brand
            return true
        }

        if (positions[1] == tag && positions[2] == tag && Board.isEmptyPosition(0)) {
            positions[0] = brand
            return true
        }

        if (positions[0] == tag && positions[2] == tag && Board.isEmptyPosition(1)) {
            positions[1] = brand
            return true
        }

        //Central Horizontal
        if (positions[3] == tag && positions[4] == tag && Board.isEmptyPosition(5)) {
            positions[5] = brand
            return true
        }

        if (positions[4] == tag && positions[5] == tag && Board.isEmptyPosition(3)) {
            positions[3] = brand
            return true
        }

        if (positions[3] == tag && positions[5] == tag && Board.isEmptyPosition(4)) {
            positions[4] = brand
            return true
        }

        //Bottom Horizontal
        if (positions[6] == tag && positions[7] == tag && Board.isEmptyPosition(8)) {
            positions[8] = brand
            return true
        }

        if (positions[7] == tag && positions[8] == tag && Board.isEmptyPosition(6)) {
            positions[6] = brand
            return true
        }

        if (positions[6] == tag && positions[8] == tag && Board.isEmptyPosition(7)) {
            positions[7] = brand
            return true
        }

        //Vertical Left
        if (positions[0] == tag && positions[3] == tag && Board.isEmptyPosition(6)) {
            positions[6] = brand
            return true
        }

        if (positions[3] == tag && positions[6] == tag && Board.isEmptyPosition(0)) {
            positions[0] = brand
            return true
        }

        if (positions[0] == tag && positions[6] == tag && Board.isEmptyPosition(3)) {
            positions[3] = brand
            return true
        }

        //Vertical Central
        if (positions[1] == tag && positions[4] == tag && Board.isEmptyPosition(7)) {
            positions[7] = brand
            return true
        }

        if (positions[4] == tag && positions[7] == tag && Board.isEmptyPosition(1)) {
            positions[1] = brand
            return true
        }

        if (positions[1] == tag && positions[7] == tag && Board.isEmptyPosition(4)) {
            positions[4] = brand
            return true
        }

        //Vertical Right
        if (positions[2] == tag && positions[5] == tag && Board.isEmptyPosition(8)) {
            positions[8] = brand
            return true
        }

        if (positions[5] == tag && positions[8] == tag && Board.isEmptyPosition(2)) {
            positions[2] = brand
            return true
        }

        if (positions[2] == tag && positions[8] == tag && Board.isEmptyPosition(5)) {
            positions[5] = brand
            return true
        }

        //Diagonals
        if (positions[0] == tag && positions[4] == tag && Board.isEmptyPosition(8)) {
            positions[8] = brand
            return true
        }

        if (positions[4] == tag && positions[8] == tag && Board.isEmptyPosition(0)) {
            positions[0] = brand
            return true
        }

        if (positions[0] == tag && positions[8] == tag && Board.isEmptyPosition(4)) {
            positions[4] = brand
            return true
        }

        if (positions[4] == tag && positions[6] == tag && Board.isEmptyPosition(2)) {
            positions[2] = brand
            return true
        }

        if (positions[4] == tag && positions[2] == tag && Board.isEmptyPosition(6)) {
            positions[6] = brand
            return true
        }

        if (positions[6] == tag && positions[2] == tag && Board.isEmptyPosition(4)) {
            positions[4] = brand
            return true
        }

        Board.positions = positions

        return false
    }

    fun analyzeAndPlay(opponentTag: Int) {
        if (canWin(tag)) {
            play()
        } else if (canWin(opponentTag)) {
            play()
        } else {
            play()
        }
    }

    companion object {
        private val brand = 10
    }
}
