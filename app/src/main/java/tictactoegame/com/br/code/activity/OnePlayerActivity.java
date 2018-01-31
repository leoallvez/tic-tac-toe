package tictactoegame.com.br.code.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import tictactoegame.com.br.code.R;
import tictactoegame.com.br.code.model.Board;
import tictactoegame.com.br.code.model.Player;
import tictactoegame.com.br.code.model.PlayerVirtual;

public class OnePlayerActivity extends AppCompatActivity {

    private PlayerVirtual playerVirtual;
    private Player player;
    private Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_player);
        initialize();
    }

    private void initialize(){
        playerVirtual = new PlayerVirtual("Machine",0);
        player = new Player ("You",1);
        board = new Board(playerVirtual,player);
    }

    public void onClick(View v) {
        //Toast.makeText(this, v.getTag().toString(), Toast.LENGTH_LONG).show();
        played(Integer.parseInt(v.getTag().toString()));
    }

    public void played(int movement) {

        if ((Board.isEmptyPosition(movement)) && (!board.gameOver())) {
            player.play(movement);

            if (!playerVirtual.won()) {
                playerVirtual.analyzeAndPlay(playerVirtual.getId());
            }
            //preencheLacunas();
            board.show();
            if (board.gameOver()) {
                //mostraResultado();
            }
        }
    }
}