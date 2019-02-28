package tictactoegame.com.br.code.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import tictactoegame.com.br.code.R
import tictactoegame.com.br.code.model.*
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_one_player.*

class OnePlayerActivity : AppCompatActivity() {

    protected var board: Board? = null
    protected var player = Player("You", 1)
    protected var playerVirtual = PlayerVirtual("Machine", 0)
    val buttons: Array<Button> = arrayOf (btn00, btn01, btn02, btn03, btn04, btn05, btn06, btn07, btn08)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_player)

        board = Board(playerVirtual, player)
        board?.start()
    }

    fun onClick(view: View) {
        played(view.tag.toString().toInt())
    }

    fun played(movement: Int) {

        if (Board.isEmptyPosition(movement).and(board!!.gameOver().not())) {

            player.play(movement)

            if (player.won()) {
                player.toScore()
            } else if (!playerVirtual.won()) {
                playerVirtual.analyzeAndPlay(player.tag)
                if (playerVirtual.won()) {
                    playerVirtual.toScore()
                }
            }
            points?.text = "Me: ${player.points}, Machine: ${playerVirtual.points}"
            fills()
        }
    }

    fun newGame(view: View) {
        board?.start()
        player.turnChange()

        if (player.turn.not()) playerVirtual.randomPlay()
        fills()
    }

    private fun fills() {

        for (i in buttons.indices) {

            when(board?.showPosition(i)) {
                player.tag -> print(i, R.color.lightBlue, "X")
                playerVirtual.tag -> print(i, R.color.colorPlayerVirtal, "0")
                else -> print(i, R.color.colorGreen, null)
            }
        }
    }

    private fun print(position: Int, color: Int, symbol: String?) {
        buttons[position]?.setBackgroundColor(resources.getColor(color))
        buttons[position]?.setText(symbol)
    }
}