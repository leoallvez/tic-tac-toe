package tictactoegame.com.br.code.model
/**
 * Created by Leonardo on 25/01/2019.
 */
open class Player(name: String, tag: Int) {

    var tag  = 0
    var name: String
    var turn = false
    var points = 0

    init{
        this.tag = tag
        this.name = name
        this.points = 0
    }

    fun turnChange() {
        turn = turn.not()
    }

    fun play(movement:Int) {
        if (Board.isEmptyPosition(movement)) {
            Board.positions[movement] = tag
        }
    }

    fun toScore() { points++ }

    fun won(): Boolean {

        val pos = Board.positions
        //Horizontal
        for(i in 0..6 step 3) {
            if(pos[i].equals(tag).and(pos[i+1].equals(tag)).and(pos[i+2].equals(tag))) return true
        }
        //Vertical
        for(i in 0..2) {
            if(pos[i].equals(tag).and(pos[i+3].equals(tag)).and(pos[i+6].equals(tag))) return true
        }
        //Diagonal
        return (pos[0].equals(tag)).and((pos[4].equals(tag))).and(pos[8].equals(tag))
                .or((pos[6].equals(tag)).and(pos[4].equals(tag)).and(pos[2].equals(tag)))
    }
}