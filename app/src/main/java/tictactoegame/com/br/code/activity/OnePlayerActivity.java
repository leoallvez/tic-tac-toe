package tictactoegame.com.br.code.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import tictactoegame.com.br.code.R;
import tictactoegame.com.br.code.model.Board;
import tictactoegame.com.br.code.model.Player;
import tictactoegame.com.br.code.model.PlayerVirtual;

public class OnePlayerActivity extends AppCompatActivity {

    private Board board;
    private Player player;
    private PlayerVirtual playerVirtual;
    private int[] buttonsId = {
            R.id.btn00, R.id.btn01, R.id.btn02,
            R.id.btn03, R.id.btn04, R.id.btn05,
            R.id.btn06, R.id.btn07, R.id.btn08
    };
    private Button[] buttons = new Button[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_player);
        initialize();
        startElements();
    }

    private void startElements() {

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = (Button) findViewById(buttonsId[i]);
        }
    }

    private void initialize() {
        playerVirtual = new PlayerVirtual("Machine", 0);
        player = new Player("You", 1);
        board = new Board(playerVirtual, player);
        board.start();
    }

    public void onClick(View v) {

        played(Integer.parseInt(v.getTag().toString()));
    }

    public void played(int movement) {

        if ((Board.isEmptyPosition(movement)) && (!board.gameOver())) {
            player.play(movement);

            if (!player.won() && !playerVirtual.won()) {
                playerVirtual.analyzeAndPlay(playerVirtual.getId());
            }
            fills();

            if (board.gameOver()) {
                //showResult();
            }
        }
    }

    public void newGame(View v) {
        board.start();
        player.turnChange();

        if (!player.getTurn()) {
            playerVirtual.randomPlay();
        }
        fills();
    }

    private void fills() {

        for (int i = 0; i < buttons.length; i++) {

            if (board.showPosition(i) == player.getId()) {
                print(i ,R.color.lightBlue, "X");
            } else if (board.showPosition(i) == playerVirtual.getId()) {
                print(i ,R.color.colorPlayerVirtal, "0");
            } else {
                print(i ,R.color.colorGreen, null);
            }
        }
    }

    private void print(int position,int color, String symbol){
        buttons[position].setBackgroundColor(getResources().getColor(color));
        buttons[position].setText(symbol);
    }
}