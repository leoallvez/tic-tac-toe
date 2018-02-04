package tictactoegame.com.br.code.model;

import java.util.Random;

/**
 * Created by Leonardo on 28/01/2018.
 */

public class PlayerVirtual extends Player {

    private static final int BRAND = 10;

    public PlayerVirtual(String name, int tag) {
        super(name,tag);
        turn = false;
    }

    private void play(){

        int higherNumber = 0, higherNumberPosition = 0;

        for(int i = 0; i < Board.positions.length; i++){
            if((Board.positions[i] > higherNumber) && (Board.isEmptyPosition(i))){
                higherNumber = Board.positions[i];
                higherNumberPosition = i;
            }
        }
        Board.positions[higherNumberPosition] = tag;
    }

    public void randomPlay (){
        Board.positions[new Random().nextInt(Board.positions.length)] = tag;
    }

    private boolean canWin(int tag){
        int[] p = Board.positions;

        //Horizontal Superior
        if((p[0] == tag) && (p[1] == tag) && (Board.isEmptyPosition(2))){p[2] = BRAND; return true;}
        if((p[1] == tag) && (p[2] == tag) && (Board.isEmptyPosition(0))){p[0] = BRAND; return true;}
        if((p[0] == tag) && (p[2] == tag) && (Board.isEmptyPosition(1))){p[1] = BRAND; return true;}

        //Central Horizontal
        if((p[3] == tag) && (p[4] == tag) && (Board.isEmptyPosition(5))){p[5] = BRAND; return true;}
        if((p[4] == tag) && (p[5] == tag) && (Board.isEmptyPosition(3))){p[3] = BRAND; return true;}
        if((p[3] == tag) && (p[5] == tag) && (Board.isEmptyPosition(4))){p[4] = BRAND; return true;}

        //Bottom Horizontal
        if((p[6] == tag) && (p[7] == tag) && (Board.isEmptyPosition(8))){p[8] = BRAND; return true;}
        if((p[7] == tag) && (p[8] == tag) && (Board.isEmptyPosition(6))){p[6] = BRAND; return true;}
        if((p[6] == tag) && (p[8] == tag) && (Board.isEmptyPosition(7))){p[7] = BRAND; return true;}

        //Vertical Left
        if((p[0] == tag) && (p[3] == tag) && (Board.isEmptyPosition(6))){p[6] = BRAND; return true;}
        if((p[3] == tag) && (p[6] == tag) && (Board.isEmptyPosition(0))){p[0] = BRAND; return true;}
        if((p[0] == tag) && (p[6] == tag) && (Board.isEmptyPosition(3))){p[3] = BRAND; return true;}

        //Vertical Central
        if((p[1] == tag) && (p[4] == tag) && (Board.isEmptyPosition(7))){p[7] = BRAND; return true;}
        if((p[4] == tag) && (p[7] == tag) && (Board.isEmptyPosition(1))){p[1] = BRAND; return true;}
        if((p[1] == tag) && (p[7] == tag) && (Board.isEmptyPosition(4))){p[4] = BRAND; return true;}

        //Vertical Right
        if((p[2] == tag) && (p[5] == tag) && (Board.isEmptyPosition(8))){p[8] = BRAND; return true;}
        if((p[5] == tag) && (p[8] == tag) && (Board.isEmptyPosition(2))){p[2] = BRAND; return true;}
        if((p[2] == tag) && (p[8] == tag) && (Board.isEmptyPosition(5))){p[5] = BRAND; return true;}

        //Diagonals
        if((p[0] == tag) && (p[4] == tag) && (Board.isEmptyPosition(8))){p[8] = BRAND; return true;}
        if((p[4] == tag) && (p[8] == tag) && (Board.isEmptyPosition(0))){p[0] = BRAND; return true;}
        if((p[0] == tag) && (p[8] == tag) && (Board.isEmptyPosition(4))){p[4] = BRAND; return true;}

        if((p[4] == tag) && (p[6] == tag) && (Board.isEmptyPosition(2))){p[2] = BRAND; return true;}
        if((p[4] == tag) && (p[2] == tag) && (Board.isEmptyPosition(6))){p[6] = BRAND; return true;}
        if((p[6] == tag) && (p[2] == tag) && (Board.isEmptyPosition(4))){p[4] = BRAND; return true;}

        Board.positions = p;

        return false;
    }

    public void analyzeAndPlay(int opponentTag){
        if(canWin(tag)){
            play();
        }else if(canWin(opponentTag)){
            play();
        }else{
            play();
        }
    }
}
