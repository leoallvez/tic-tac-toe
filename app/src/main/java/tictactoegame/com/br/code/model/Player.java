package tictactoegame.com.br.code.model;

/**
 * Created by Leonardo on 28/01/2018.
 */

public class Player {

    protected int id;
    protected String name;
    protected boolean turn;
    protected int points;

    public Player (String name, int id){
        this.id = id;
        this.name = name;
        this.points = 0;
    }

    public void turnChange(){
        turn = !turn;
    }

    public boolean getTurn(){return turn;}

    public void play(int movement){

        if(Board.isEmptyPosition(movement)){
            Board.positions[movement] = id;
        }
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
        int[] p = Board.positions;
             //Horizontal
        return (
            ((p[0] == id)&&(p[1] == id)&&(p[2] == id)) ||
            ((p[3] == id)&&(p[4] == id)&&(p[5] == id)) ||
            ((p[6] == id)&&(p[7] == id)&&(p[8] == id)) ||
            //Vertical
            ((p[0] == id)&&(p[3] == id)&&(p[6] == id)) ||
            ((p[1] == id)&&(p[4] == id)&&(p[7] == id)) ||
            ((p[2] == id)&&(p[5] == id)&&(p[8] == id)) ||
            //Diagonals
            ((p[0] == id)&&(p[4] == id)&&(p[8] == id)) ||
            ((p[6] == id)&&(p[4] == id)&&(p[2] == id))
        );
    }
}
