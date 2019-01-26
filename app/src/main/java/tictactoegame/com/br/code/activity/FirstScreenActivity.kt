package tictactoegame.com.br.code.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

import tictactoegame.com.br.code.R

class FirstScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_screen)
    }

    fun startOnePlayer(v: View) {
        startActivity(Intent(this, OnePlayerActivity::class.java))
    }

    fun startTwoPlayers(v: View) {
        startActivity(Intent(this, TwoPlayersActivity::class.java))
    }
}