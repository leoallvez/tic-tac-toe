package tictactoegame.com.br.code.model;

/**
 * Created by Leonardo on 28/01/2018.
 */

public class Player {

    protected int tag;
    protected String name;
    protected boolean turn;
    protected int points;

    public Player (String name, int tag){
        this.tag = tag;
        this.name = name;
        this.points = 0;
    }

    public void turnChange(){
        turn = !turn;
    }

    public boolean getTurn(){return turn;}

    public void play(int movement){

        if(Board.isEmptyPosition(movement)){
            Board.positions[movement] = tag;
        }
    }

    public int getPoints() {
        return points;
    }

    public void toScore() {
        points++;
    }

    public int getTag() {
        return tag;
    }

    public void restartPunctuation(){
        points = 0;
    }

    public boolean won(){
        int[] p = Board.positions;
             //Horizontal
        return (
            ((p[0] == tag)&&(p[1] == tag)&&(p[2] == tag)) ||
            ((p[3] == tag)&&(p[4] == tag)&&(p[5] == tag)) ||
            ((p[6] == tag)&&(p[7] == tag)&&(p[8] == tag)) ||
            //Vertical
            ((p[0] == tag)&&(p[3] == tag)&&(p[6] == tag)) ||
            ((p[1] == tag)&&(p[4] == tag)&&(p[7] == tag)) ||
            ((p[2] == tag)&&(p[5] == tag)&&(p[8] == tag)) ||
            //Diagonals
            ((p[0] == tag)&&(p[4] == tag)&&(p[8] == tag)) ||
            ((p[6] == tag)&&(p[4] == tag)&&(p[2] == tag))
        );
    }
}
