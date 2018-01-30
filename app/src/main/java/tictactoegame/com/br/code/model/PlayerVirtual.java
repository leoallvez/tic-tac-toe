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
        int movement = random.nextInt(9);
        Board.positions[movement] = id;
    }

    private boolean canWin(int id){
        int[] b = Board.positions;

        //Horizontal Superior
        if((b[0] == id) && (b[1] == id) && (Board.isEmptyPosition(2))){b[2] = BRAND; return true;}
        if((b[1] == id) && (b[2] == id) && (Board.isEmptyPosition(0))){b[0] = BRAND; return true;}
        if((b[0] == id) && (b[2] == id) && (Board.isEmptyPosition(1))){b[1] = BRAND; return true;}

        //Central Horizontal
        if((b[3] == id) && (b[4] == id) && (Board.isEmptyPosition(5))){b[5] = BRAND; return true;}
        if((b[4] == id) && (b[5] == id) && (Board.isEmptyPosition(3))){b[3] = BRAND; return true;}
        if((b[3] == id) && (b[5] == id) && (Board.isEmptyPosition(4))){b[4] = BRAND; return true;}

        //Bottom Horizontal
        if((b[6] == id) && (b[7] == id) && (Board.isEmptyPosition(8))){b[8] = BRAND; return true;}
        if((b[7] == id) && (b[8] == id) && (Board.isEmptyPosition(6))){b[6] = BRAND; return true;}
        if((b[6] == id) && (b[8] == id) && (Board.isEmptyPosition(7))){b[7] = BRAND; return true;}

        //Vertical Left
        if((b[0] == id) && (b[3] == id) && (Board.isEmptyPosition(6))){b[6] = BRAND; return true;}
        if((b[3] == id) && (b[6] == id) && (Board.isEmptyPosition(0))){b[0] = BRAND; return true;}
        if((b[0] == id) && (b[6] == id) && (Board.isEmptyPosition(3))){b[3] = BRAND; return true;}

        //Vertical Central
        if((b[1] == id) && (b[4] == id) && (Board.isEmptyPosition(7))){b[7] = BRAND; return true;}
        if((b[4] == id) && (b[7] == id) && (Board.isEmptyPosition(1))){b[1] = BRAND; return true;}
        if((b[1] == id) && (b[7] == id) && (Board.isEmptyPosition(4))){b[4] = BRAND; return true;}

        //Vertical Right
        if((b[2] == id) && (b[5] == id) && (Board.isEmptyPosition(8))){b[8] = BRAND; return true;}
        if((b[5] == id) && (b[8] == id) && (Board.isEmptyPosition(2))){b[2] = BRAND; return true;}
        if((b[2] == id) && (b[8] == id) && (Board.isEmptyPosition(5))){b[5] = BRAND; return true;}

        //Diagonals
        if((b[0] == id) && (b[4] == id) && (Board.isEmptyPosition(8))){b[8] = BRAND; return true;}
        if((b[4] == id) && (b[8] == id) && (Board.isEmptyPosition(0))){b[0] = BRAND; return true;}
        if((b[0] == id) && (b[8] == id) && (Board.isEmptyPosition(4))){b[4] = BRAND; return true;}

        if((b[4] == id) && (b[6] == id) && (Board.isEmptyPosition(2))){b[2] = BRAND; return true;}
        if((b[4] == id) && (b[2] == id) && (Board.isEmptyPosition(6))){b[6] = BRAND; return true;}
        if((b[6] == id) && (b[2] == id) && (Board.isEmptyPosition(4))){b[4] = BRAND; return true;}

        Board.positions = b;

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
