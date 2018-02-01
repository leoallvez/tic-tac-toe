package tictactoegame.com.br.code.activity;

import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import tictactoegame.com.br.code.R;
import tictactoegame.com.br.code.model.Board;
import tictactoegame.com.br.code.model.Player;
import android.support.v7.app.AppCompatActivity;
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

        for(int i = 0; i < buttons.length; i++){
            buttons[i] = (Button) findViewById(buttonsId[i]);
        }
    }

    private void initialize(){
        playerVirtual = new PlayerVirtual("Machine",0);
        player = new Player ("You",1);
        board = new Board(playerVirtual,player);
        board.start();
    }

    public void onClick(View v) {

        played(Integer.parseInt(v.getTag().toString()));
    }

    public void played(int movement) {

        if ((Board.isEmptyPosition(movement)) && (!board.gameOver())) {
            player.play(movement);

            if (!playerVirtual.won()) {
                playerVirtual.analyzeAndPlay(playerVirtual.getId());
            }
            fills();
            board.show();
            if (board.gameOver()) {
                //showResult();
            }
        }
    }

    public void newGame(View v){
        board.start();
        fills();
        player.turnChange();
        if(playerVirtual.getTurn()){playerVirtual.randomPlay();}
    }

    private void fills(){
        int[]a = Board.positions;
        for(int i = 0; i < a.length; i++){

            if(board.showPosition(i) == player.getId()){
                buttons[i].setBackgroundColor(getResources().getColor(R.color.lightBlue));
                buttons[i].setText("X");
            }else if (board.showPosition(i) == playerVirtual.getId()){
                buttons[i].setBackgroundColor(getResources().getColor(R.color.colorPlayerVirtal));
                buttons[i].setText("O");
            }else{
                buttons[i].setBackgroundColor(getResources().getColor(R.color.colorGreen));
                buttons[i].setText("");
            }
        }
    }
}