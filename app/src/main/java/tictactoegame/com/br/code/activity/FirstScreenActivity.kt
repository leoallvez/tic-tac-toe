package tictactoegame.com.br.code.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_first_screen.*
import tictactoegame.com.br.code.R

class FirstScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_screen)

        btnOnePlayer.setOnClickListener {
            startActivity(Intent(this, OnePlayerActivity::class.java))
        }
    }
}