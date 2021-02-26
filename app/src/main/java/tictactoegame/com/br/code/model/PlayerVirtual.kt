package tictactoegame.com.br.code.model

import tictactoegame.com.br.code.model.Board.Companion.positions
import java.util.*

/*
 * Created by Leonardo on 25/01/2019.
 */

class PlayerVirtual(name: String = "Machine", tag: Int = 0) : Player(name, tag) {

    private fun play() {

        var higherNumber = 0
        var higherNumberPosition = 0

        positions.forEachIndexed { index, element ->
            if ((element > higherNumber).and(Board.isEmptyPosition(index))) {
                higherNumber = element
                higherNumberPosition = index
            }
        }

        positions[higherNumberPosition] = seed
    }

    fun randomPlay(){
        positions[Random().nextInt(positions.size)] = seed
    }

    private fun canWin(tagOpponent: Int) = when(true) {

        rule1(tagOpponent) -> true
        rule2()            -> true
        rule2()            -> true
        rule3()            -> true
        rule4(tagOpponent) -> true
        rule5()            -> true
        else               -> false
    }

    // 1 - se o oponente ocupar duas casas seguidas, ocupe a terceira
    private fun rule1(tagOpponent: Int): Boolean {

        if (positions[0] == tagOpponent && positions[4] == tagOpponent && Board.isEmptyPosition(8)) {
            positions[8] = brand
            return true
        }

        if (positions[4] == tagOpponent && positions[8] == tagOpponent && Board.isEmptyPosition(0)) {
            positions[0] = brand
            return true
        }

        if (positions[0] == tagOpponent && positions[8] == tagOpponent && Board.isEmptyPosition(4)) {
            positions[4] = brand
            return true
        }

        if (positions[4] == tagOpponent && positions[6] == tagOpponent && Board.isEmptyPosition(2)) {
            positions[2] = brand
            return true
        }

        if (positions[4] == tagOpponent && positions[2] == tagOpponent && Board.isEmptyPosition(6)) {
            positions[6] = brand
            return true
        }

        if (positions[6] == tagOpponent && positions[2] == tagOpponent && Board.isEmptyPosition(4)) {
            positions[4] = brand
            return true
        }

        //Horizontal --- rows
        for (i in 0..6 step 3) {
            if (positions[i] == tagOpponent && positions[i+1] == tagOpponent && Board.isEmptyPosition(i+2)) {
                positions[i+2] = brand
                return true
            }

            if (positions[i] == tagOpponent && positions[i+2] == tagOpponent && Board.isEmptyPosition(i+1)) {
                positions[i+1] = brand
                return true
            }

            if (positions[i+1] == tagOpponent && positions[i+2] == tagOpponent && Board.isEmptyPosition(i)) {
                positions[i] = brand
                return true
            }
        }

        //Vertical --- rows
        for (i in 0..2) {
            if (positions[i] == tagOpponent && positions[i+3] == tagOpponent && Board.isEmptyPosition(i+6)) {
                positions[i+6] = brand
                return true
            }

            if (positions[i+6] == tagOpponent && positions[i+3] == tagOpponent && Board.isEmptyPosition(i)) {
                positions[i] = brand
                return true
            }

            if (positions[i] == tagOpponent && positions[i+6] == tagOpponent && Board.isEmptyPosition(i+3)) {
                positions[i+3] = brand
                return true
            }
        }
        return false
    }
    // 2 - caso contrário se algum movimento que cria duas linhas com
    // duas casas ocupadas, faça ele.
    private fun rule2() : Boolean {

        if (positions[6] == seed && positions[2] == seed && Board.isEmptyPosition(0)) {
            positions[0] = brand
            return true
        }

        if (positions[6] == seed && positions[2] == seed && Board.isEmptyPosition(8)) {
            positions[8] = brand
            return true
        }

        if (positions[0] == seed && positions[8] == seed && Board.isEmptyPosition(2)) {
            positions[2] = brand
            return true
        }

        if (positions[0] == seed && positions[8] == seed && Board.isEmptyPosition(6)) {
            positions[6] = brand
            return true
        }

        return false
    }
    // 3 - caso contrário, se o espaço do centro estive vazio, ocupe ele
    private fun rule3(): Boolean{
        if(Board.isEmptyPosition(4)) {
            positions[4] = brand
            return true
        }
        return false
    }
    // 4 - caso contrário, se o oponente preencheu uma quina, preencha a
    // quina contrária.
    private fun rule4(tagOpponent: Int): Boolean {

        if (positions[0] == tagOpponent && Board.isEmptyPosition(4) && Board.isEmptyPosition(8)) {
            positions[8] = brand
            return true
        }

        if (positions[8] == tagOpponent && Board.isEmptyPosition(4) && Board.isEmptyPosition(0)) {
            positions[0] = brand
            return true
        }

        if (positions[6] == tagOpponent && Board.isEmptyPosition(4) && Board.isEmptyPosition(2)) {
            positions[2] = brand
            return true
        }

        if (positions[2] == tagOpponent && Board.isEmptyPosition(4) && Board.isEmptyPosition(6)) {
            positions[6] = brand
            return true
        }

        return false
    }
    // 5 - caso contrário, se tiver uma quina vázia preencha ela.
    private fun rule5(): Boolean {
        if (Board.isEmptyPosition(0)) {
            positions[0] = brand
            return true
        }

        if (Board.isEmptyPosition(2)) {
            positions[2] = brand
            return true
        }

        if (Board.isEmptyPosition(6)) {
            positions[6] = brand
            return true
        }

        if (Board.isEmptyPosition(8)) {
            positions[8] = brand
            return true
        }
        return false
    }
    // 6 - se nenhuma dessas condições acontencer, pode preencher qualquer
    // espaço vázio.

    fun analyzeAndPlay(opponentTag: Int) {
        canWin(opponentTag)
        play()
    }

    companion object {
        private const val brand = 10
    }
}
