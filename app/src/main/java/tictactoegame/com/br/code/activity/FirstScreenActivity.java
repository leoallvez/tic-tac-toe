package tictactoegame.com.br.code.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import tictactoegame.com.br.code.R;

public class FirstScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);

        final EditText etPlayer1 = (EditText) findViewById(R.id.etPlayer1);
        final EditText etPlayer2 = (EditText) findViewById(R.id.etPlayer2);

        Button bPlay = (Button) findViewById(R.id.bPlay);

        bPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Getting strings from edit text
                String PLAYER_1 = "" + etPlayer1.getText().toString();
                String PLAYER_2 = "" + etPlayer2.getText().toString();

                //Intents are used to shift from one activity to another.
                //We can also transfer data with intents.

                //Creating an object of intent through which we can go from current activity to MainAcivity
                Intent i = new Intent(FirstScreenActivity.this,MainActivity.class);

                //Adding data with intent to transfer.
                i.putExtra("PLAYER_1",PLAYER_1);
                i.putExtra("PLAYER_2",PLAYER_2);

                //Calling to intent to open MainActivity.
                startActivity(i);

            }
        });
    }
}