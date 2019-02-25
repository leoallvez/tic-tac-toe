package tictactoegame.com.br.code.model

/*
 Created by Leonardo on 25/01/2019.
*/
class Board(playerOne:Player, playerTwo:Player) {

    val playerOne:Player
    val playerTwo:Player
    val isFullyPopulated:Boolean
        get() {
            var count = 0
            for (position in positions) {
                if (position.equals(playerOne.tag) || position.equals(playerTwo.tag)) {
                    count++
                }
            }
            return count == Board.positions.size
        }

    init{

        this.playerOne = playerOne
        this.playerTwo = playerTwo
        start()
    }

    fun start() {
        positions = intArrayOf(3, 2, 3, 2, 4, 2, 3, 2, 3)
    }

    fun showPosition(movement:Int):Int {
        return positions[movement]
    }

    fun gameOver()= isFullyPopulated || playerOne.won() || playerTwo.won()

    companion object {
        var positions = IntArray(9)
        val minimum = 2
        fun isEmptyPosition(movement:Int)= positions[movement] >= minimum
    }
}