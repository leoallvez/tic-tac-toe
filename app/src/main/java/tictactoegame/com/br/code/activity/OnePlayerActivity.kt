package tictactoegame.com.br.code.activity

import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import kotlinx.android.synthetic.main.activity_one_player.*
import tictactoegame.com.br.code.R
import tictactoegame.com.br.code.model.Board
import tictactoegame.com.br.code.model.Player
import tictactoegame.com.br.code.model.PlayerVirtual

class OnePlayerActivity : AppCompatActivity() {

    private val player = Player()
    private val playerVirtual = PlayerVirtual()
    private val board = Board(playerVirtual, player)
    private var buttons: Array<Button>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_player)

        buttons  = arrayOf(btn00, btn01, btn02, btn03, btn04, btn05, btn06, btn07, btn08)
        buttons?.forEach{ it ->
            it.setOnClickListener{ played(it.tag.toString().toInt()) }
        }
        bReset.setOnClickListener{
            newGame()
        }
        board.start()
        newGame()
    }

    private fun played(movement: Int) {

        if (Board.isEmptyPosition(movement).and(board.gameOver().not())) {

            player.play(movement)
            if (player.won()) { player.toScore() }
            if (board.gameOver().not()) {
                if (!playerVirtual.won()) {
                    playerVirtual.analyzeAndPlay(player.tag)
                    if (playerVirtual.won()) {
                        playerVirtual.toScore()
                    }
                }
            }

            fills()
        }
    }

    private fun newGame() {
        board.start()
        //player.turnChange()
        //if (player.turn.not()) playerVirtual.randomPlay()
        playerVirtual.randomPlay()
        fills()
    }

    private fun fills() {
        points.text = getString(R.string.score, player.points, playerVirtual.points)
        buttons?.indices?.forEach {
            when(board.showPosition(it)) {
                player.tag -> print(it, R.color.lightBlue, "X")
                playerVirtual.tag -> print(it, R.color.colorPlayerVirtal, "0")
                else -> print(it, R.color.colorGreen, null)
            }
        }
    }

    private fun print(position: Int, colorId: Int, symbol: String?) {
        val color = ResourcesCompat.getColor(resources, colorId, null)
        buttons?.get(position)?.setBackgroundColor(color)
        buttons?.get(position)?.text = symbol
    }
}