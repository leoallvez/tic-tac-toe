package tictactoegame.com.br.code.model

import java.util.*

/*
 * Created by Leonardo on 25/01/2019.
 */

class PlayerVirtual(name: String = "Machine", tag: Int = 0) : Player(name, tag) {

    private fun play() {

        var higherNumber = 0
        var higherNumberPosition = 0


        for(i in 0 until Board.positions.count()) {
            if ((Board.positions[i] > higherNumber).and(Board.isEmptyPosition(i))) {
                higherNumber = Board.positions[i]
                higherNumberPosition = i
            }
        }
        Board.positions[higherNumberPosition] = tag
    }

    fun randomPlay(){
        Board.positions[Random().nextInt(Board.positions.size)] = tag
    }

    private fun canWin(tag: Int, isOpponent: Boolean = true): Boolean {

        val positions = Board.positions

        /**
         * 0 | 1 | 2
         * 3 | 4 | 5
         * 6 | 7 | 8
         */

        //Horizontal --- rows
        for (i in 0..6 step 3) {
            if (positions[i] == tag && positions[i+1] == tag && Board.isEmptyPosition(i+2)) {
                positions[i+2] = brand
                return true
            }

            if (positions[i] == tag && positions[i+2] == tag && Board.isEmptyPosition(i+1)) {
                positions[i+1] = brand
                return true
            }

            if (positions[i+1] == tag && positions[i+2] == tag && Board.isEmptyPosition(i)) {
                positions[i] = brand
                return true
            }
        }

        //Vertical --- rows
        for (i in 0..2) {
            if (positions[i] == tag && positions[i+3] == tag && Board.isEmptyPosition(i+6)) {
                positions[i+6] = brand
                return true
            }

            if (positions[i+6] == tag && positions[i+3] == tag && Board.isEmptyPosition(i)) {
                positions[i] = brand
                return true
            }

            if (positions[i] == tag && positions[i+6] == tag && Board.isEmptyPosition(i+3)) {
                positions[i+3] = brand
                return true
            }
        }

        if(isOpponent) {

            if (positions[0] == tag && Board.isEmptyPosition(4) && Board.isEmptyPosition(8)) {
                positions[8] = brand
                return true
            }

            if (positions[8] == tag && Board.isEmptyPosition(4) && Board.isEmptyPosition(0)) {
                positions[0] = brand
                return true
            }

            if (positions[6] == tag && Board.isEmptyPosition(4) && Board.isEmptyPosition(2)) {
                positions[2] = brand
                return true
            }

            if (positions[2] == tag && Board.isEmptyPosition(4) && Board.isEmptyPosition(6)) {
                positions[6] = brand
                return true
            }
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

        if (positions[Position.fromInt(6).number] == tag && positions[2] == tag && Board.isEmptyPosition(4)) {
            positions[4] = brand
            return true
        }

        Board.positions = positions

        return false
    }
    // 1 - se o oponente ocupar duas casas seguidas, ocupe a terceira
    // 2 - caso contrário se algum movimento que cria duas linhar com
    // duas casas ocupadas, faça ele.
    // 3 - caso contrário, se o espaço do centro estive vazio, ocupe ele
    fun centerIsEmpty(): VictoryAnalysis{
        val position = Position.CENTER_CENTER.number
        return VictoryAnalysis(Board.isEmptyPosition(position), position )
    }
    // 4 - caso contrário, se o oponente preencheu uma quina, preencha a
    // quina contrária.
    // 5 - caso contrário, se tiver uma quina vázia preencha ela.
    // 6 - se nenhuma dessas condições acontencer, pode preencher qualquer
    // espaço vázio.

    fun analyzeAndPlay(opponentTag: Int) {
        if (canWin(tag).not()) { canWin(opponentTag, true) }
        play()
    }

    companion object {
        private val brand = 10
    }

    inner class VictoryAnalysis(val win: Boolean, val position: Int)
}
