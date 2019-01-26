package tictactoegame.com.br.code.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView

import tictactoegame.com.br.code.R
import tictactoegame.com.br.code.model.Board
import tictactoegame.com.br.code.model.Player
import tictactoegame.com.br.code.model.PlayerVirtual

class OnePlayerActivity : AppCompatActivity() {

    private var board: Board? = null
    private var player: Player? = null
    private var playerVirtual: PlayerVirtual? = null
    private var points: TextView? = null
    private val buttonsId = intArrayOf(R.id.btn00, R.id.btn01, R.id.btn02, R.id.btn03, R.id.btn04, R.id.btn05, R.id.btn06, R.id.btn07, R.id.btn08)
    private val buttons = arrayOfNulls<Button>(9)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_player)
        initialize()
        startElements()
    }

    private fun startElements() {

        for (i in buttons.indices) {
            buttons[i] = findViewById(buttonsId[i])
        }

        points = findViewById(R.id.points)
    }

    private fun initialize() {
        playerVirtual = PlayerVirtual("Machine", 0)
        player = Player("You", 1)
        board = Board(playerVirtual!!, player!!)
        board!!.start()
    }

    fun onClick(v: View) {

        played(Integer.parseInt(v.tag.toString()))
    }

    fun played(movement: Int) {

        if (Board.isEmptyPosition(movement) && !board!!.gameOver()) {
            player!!.play(movement)

            if (player!!.won()) {
                player!!.toScore()
            } else if (!playerVirtual!!.won()) {
                playerVirtual!!.analyzeAndPlay(player!!.tag)
                if (playerVirtual!!.won()) {
                    playerVirtual!!.toScore()
                }
            }
            points!!.text = ("Me: " + player!!.points
                    + ", Machine: " + playerVirtual!!.points)
            fills()
        }
    }

    fun newGame(v: View) {
        board!!.start()
        player!!.turnChange()

        if (!player!!.turn) {
            playerVirtual!!.randomPlay()
        }
        fills()
    }

    private fun fills() {

        for (i in buttons.indices) {

            if (board!!.showPosition(i) == player!!.tag) {
                print(i, R.color.lightBlue, "X")
            } else if (board!!.showPosition(i) == playerVirtual!!.tag) {
                print(i, R.color.colorPlayerVirtal, "0")
            } else {
                print(i, R.color.colorGreen, null)
            }
        }
    }

    private fun print(position: Int, color: Int, symbol: String?) {
        buttons[position]?.setBackgroundColor(resources.getColor(color))
        buttons[position]?.setText(symbol)
    }
}