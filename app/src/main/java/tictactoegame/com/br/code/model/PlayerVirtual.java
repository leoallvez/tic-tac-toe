package tictactoegame.com.br.code.model;

import java.util.Random;

/**
 * Created by Leonardo on 28/01/2018.
 */

public class PlayerVirtual extends Player {

    private static final int BRAND = 10;

    public PlayerVirtual(String name, int id) {
        super(name,id);
        turn = false;
    }

    private void play(){
        int bigger = 0, x = 0;

        for(int i = 0; i < Board.positions.length; i++){
            if((Board.positions[i] > bigger) && (Board.isEmptyPosition(i))){
                bigger = Board.positions[i];
                x = i;
            }
        }
        Board.positions[x] = id;
    }

    public void randomPlay (){
        Random random = new Random();
        Board.positions[random.nextInt(9)] = id;
    }

    private boolean canWin(int id){
        int[] p = Board.positions;

        //Horizontal Superior
        if((p[0] == id) && (p[1] == id) && (Board.isEmptyPosition(2))){p[2] = BRAND; return true;}
        if((p[1] == id) && (p[2] == id) && (Board.isEmptyPosition(0))){p[0] = BRAND; return true;}
        if((p[0] == id) && (p[2] == id) && (Board.isEmptyPosition(1))){p[1] = BRAND; return true;}

        //Central Horizontal
        if((p[3] == id) && (p[4] == id) && (Board.isEmptyPosition(5))){p[5] = BRAND; return true;}
        if((p[4] == id) && (p[5] == id) && (Board.isEmptyPosition(3))){p[3] = BRAND; return true;}
        if((p[3] == id) && (p[5] == id) && (Board.isEmptyPosition(4))){p[4] = BRAND; return true;}

        //Bottom Horizontal
        if((p[6] == id) && (p[7] == id) && (Board.isEmptyPosition(8))){p[8] = BRAND; return true;}
        if((p[7] == id) && (p[8] == id) && (Board.isEmptyPosition(6))){p[6] = BRAND; return true;}
        if((p[6] == id) && (p[8] == id) && (Board.isEmptyPosition(7))){p[7] = BRAND; return true;}

        //Vertical Left
        if((p[0] == id) && (p[3] == id) && (Board.isEmptyPosition(6))){p[6] = BRAND; return true;}
        if((p[3] == id) && (p[6] == id) && (Board.isEmptyPosition(0))){p[0] = BRAND; return true;}
        if((p[0] == id) && (p[6] == id) && (Board.isEmptyPosition(3))){p[3] = BRAND; return true;}

        //Vertical Central
        if((p[1] == id) && (p[4] == id) && (Board.isEmptyPosition(7))){p[7] = BRAND; return true;}
        if((p[4] == id) && (p[7] == id) && (Board.isEmptyPosition(1))){p[1] = BRAND; return true;}
        if((p[1] == id) && (p[7] == id) && (Board.isEmptyPosition(4))){p[4] = BRAND; return true;}

        //Vertical Right
        if((p[2] == id) && (p[5] == id) && (Board.isEmptyPosition(8))){p[8] = BRAND; return true;}
        if((p[5] == id) && (p[8] == id) && (Board.isEmptyPosition(2))){p[2] = BRAND; return true;}
        if((p[2] == id) && (p[8] == id) && (Board.isEmptyPosition(5))){p[5] = BRAND; return true;}

        //Diagonals
        if((p[0] == id) && (p[4] == id) && (Board.isEmptyPosition(8))){p[8] = BRAND; return true;}
        if((p[4] == id) && (p[8] == id) && (Board.isEmptyPosition(0))){p[0] = BRAND; return true;}
        if((p[0] == id) && (p[8] == id) && (Board.isEmptyPosition(4))){p[4] = BRAND; return true;}

        if((p[4] == id) && (p[6] == id) && (Board.isEmptyPosition(2))){p[2] = BRAND; return true;}
        if((p[4] == id) && (p[2] == id) && (Board.isEmptyPosition(6))){p[6] = BRAND; return true;}
        if((p[6] == id) && (p[2] == id) && (Board.isEmptyPosition(4))){p[4] = BRAND; return true;}

        Board.positions = p;

        return false;
    }

    public void analyzeAndPlay(int opponentId){
        if(canWin(id)){
            play();
        }else if(canWin(opponentId)){
            play();
        }else{
            play();
        }
    }
}
