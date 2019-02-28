package tictactoegame.com.br.code.model
/**
 * Created by Leonardo on 25/01/2019.
 */
open class Player(name:String, tag:Int) {

    var tag  = 0
    var name: String
    var turn = false
    var points:Int = 0

    init{
        this.tag = tag
        this.name = name
        this.points = 0
    }

    fun turnChange() {
        turn = !turn
    }

    fun play(movement:Int) {
        if (Board.isEmptyPosition(movement)) {
            Board.positions[movement] = tag
        }
    }

    fun toScore() { points++ }

    fun restartPunctuation() {
        points = 0
    }

    fun won():Boolean {
        val positions = Board.positions
        //Horizontal
        return ((((positions[0] == tag) && (positions[1] == tag) && (positions[2] == tag)) ||
                ((positions[3] == tag) && (positions[4] == tag) && (positions[5] == tag)) ||
                ((positions[6] == tag) && (positions[7] == tag) && (positions[8] == tag)) ||
                //Vertical
                ((positions[0] == tag) && (positions[3] == tag) && (positions[6] == tag)) ||
                ((positions[1] == tag) && (positions[4] == tag) && (positions[7] == tag)) ||
                ((positions[2] == tag) && (positions[5] == tag) && (positions[8] == tag)) ||
                //Diagonals
                ((positions[0] == tag) && (positions[4] == tag) && (positions[8] == tag)) ||
                ((positions[6] == tag) && (positions[4] == tag) && (positions[2] == tag))))
    }
}