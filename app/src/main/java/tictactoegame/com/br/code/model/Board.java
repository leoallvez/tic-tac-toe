package tictactoegame.com.br.code.model;

/**
 * Created by Leonardo on 28/01/2018.
 */

public class Board {

    private Player playerOne;
    private Player playerTwo;
    public static int[] positions;
    public static final int MINIMUM = 2;


    public Board(Player playerOne, Player playerTwo) {

        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        positions = new int[9];
        start();
    }

    public void start() {
        positions = new int[]{3, 2, 3, 2, 4, 2, 3, 2, 3};
    }

    public int showPosition(int movement) {
        return positions[movement];
    }

    public boolean isFullyPopulated() {
        int count = 0;
        for (int position : positions) {
            if (position == playerOne.getId() || position == playerTwo.getId()) {
                count++;
            }
        }
        return count == Board.positions.length;
    }

    public boolean gameOver() {

        return isFullyPopulated() || playerOne.won() ||  playerTwo.won();
    }

    public static boolean isEmptyPosition(int movement){
        return positions[movement] >= MINIMUM;
    }

}
