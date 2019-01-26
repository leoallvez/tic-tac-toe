package tictactoegame.com.br.code.model
/**
 * Created by Leonardo on 25/01/2019.
 */
open class Player(name:String, tag:Int) {
    var tag  = 0
    var name:String
    var turn = false
        protected set
    var points:Int = 0
        protected set

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
        val p = Board.positions
        //Horizontal
        return ((((p[0] == tag) && (p[1] == tag) && (p[2] == tag)) ||
                ((p[3] == tag) && (p[4] == tag) && (p[5] == tag)) ||
                ((p[6] == tag) && (p[7] == tag) && (p[8] == tag)) ||
                //Vertical
                ((p[0] == tag) && (p[3] == tag) && (p[6] == tag)) ||
                ((p[1] == tag) && (p[4] == tag) && (p[7] == tag)) ||
                ((p[2] == tag) && (p[5] == tag) && (p[8] == tag)) ||
                //Diagonals
                ((p[0] == tag) && (p[4] == tag) && (p[8] == tag)) ||
                ((p[6] == tag) && (p[4] == tag) && (p[2] == tag))))
    }
}