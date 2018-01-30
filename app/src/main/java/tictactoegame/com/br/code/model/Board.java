package tictactoegame.com.br.code.model;

/**
 * Created by Leonardo on 28/01/2018.
 */

public class Board {

    private Player player1;
    private Player player2;
    public static int[] positions;
    public static final int min = 2;


    public Board(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        positions = new int[9];
        start();
    }

    public void start() {
        boolean x = true;
        for (int i = 0; i < positions.length; i++) {

            if (i == 4) {
                positions[i] = 4;
            } else if (x == true) {
                positions[i] = 3;
            } else {
                positions[i] = 2;
            }
            x = !x;
        }
    }

    public int showPosition(int movement) {
        return positions[movement];
    }

    public boolean isFullyPopulated() {
        int i = 0;
        for (int posicoe : positions) {
            if ((posicoe == player1.getId()) || (posicoe == player2.getId())) {
                i++;
            }
        }
        return i == Board.positions.length;
    }

    public boolean gameOver() {
        if (isFullyPopulated()) {
            return true;
        }
        if (player1.won()) {
            return true;
        }

        return player2.won();
    }

    public static boolean isEmptyPosition(int movement){
        return positions[movement] >= min;
    }

    public void show(){
        System.out.println();
        System.out.println();
        for(int i = 0; i < positions.length; i++){
            System.out.print(positions[i]);
            if(i % 3 == 2){System.out.println();}
        }
    }
}
