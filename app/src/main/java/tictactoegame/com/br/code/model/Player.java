package tictactoegame.com.br.code.model;

/**
 * Created by Leonardo on 28/01/2018.
 */

public class Player {

    protected String name;
    protected boolean turn;
    protected int id;
    protected int points;

    public Player (String name, int id){
        this.name = name;
        this.id = id;
        this.points = 0;
    }

    public void turnChange(){
        turn = !turn;
    }

    public boolean getTurn(){return turn;}

    public void play(int movement){
        if(Board.isEmptyPosition(movement)){Board.positions[movement] = id;}
    }

    public int getPoints() {
        return points;
    }

    public void toScore() {
        points++;
    }

    public int getId() {
        return id;
    }

    public void restartPunctuation(){
        points = 0;
    }

    public boolean won(){
        int[] t = Board.positions;
             //Horizontal
        return (
            ((t[0] == id)&&(t[1] == id)&&(t[2] == id)) ||
            ((t[3] == id)&&(t[4] == id)&&(t[5] == id)) ||
            ((t[6] == id)&&(t[7] == id)&&(t[8] == id)) ||
            //Vertical
            ((t[0] == id)&&(t[3] == id)&&(t[6] == id)) ||
            ((t[1] == id)&&(t[4] == id)&&(t[7] == id)) ||
            ((t[2] == id)&&(t[5] == id)&&(t[8] == id)) ||
            //Diagonals
            ((t[0] == id)&&(t[4] == id)&&(t[8] == id)) ||
            ((t[6] == id)&&(t[4] == id)&&(t[2] == id))
        );
    }
}
