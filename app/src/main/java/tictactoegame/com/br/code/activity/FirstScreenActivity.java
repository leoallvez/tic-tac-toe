package tictactoegame.com.br.code.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tictactoegame.com.br.code.R;

public class FirstScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
    }

    public void startOnePlayer(View v) {
        startActivity(new Intent(this,OnePlayerActivity.class));
    }

    public void startTwoPlayers(View v) {
        startActivity(new Intent(this,TwoPlayersActivity.class));
    }
}