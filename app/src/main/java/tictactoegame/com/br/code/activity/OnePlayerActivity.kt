package tictactoegame.com.br.code.activity

import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import kotlinx.android.synthetic.main.activity_one_player.*
import tictactoegame.com.br.code.R
import tictactoegame.com.br.code.Seed
import tictactoegame.com.br.code.model.*

class OnePlayerActivity : AppCompatActivity() {

    private val humanPlayer = HumanPlayer(Seed.O)
    private val virtualPlayer = VirtualPlayer(Seed.X)
    private val board = Board
    private val buttons: List<Button> by lazy {
        listOf(
                btnRow0Col0, btnRow0Col1, btnRow0Col2,
                btnRow1Col0, btnRow1Col1, btnRow1Col2,
                btnRow2Col0, btnRow2Col1, btnRow2Col2
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_player)

        buttons.forEach {
            val positions = it.tag.toString().split(":")
            val row = positions[0].toInt()
            val col = positions[1].toInt()
            it.setOnClickListener { played(row, col) }
        }
        bReset.setOnClickListener {
            newGame()
        }
        board.restart()
        newGame()
    }

    private fun played(row: Int, col: Int) {

        if (notGameOver(row, col)) {
            humanPlayer.play(row, col)
            if (humanPlayer.won()) { humanPlayer.toScore() }
            if (notGameOver(row, col).not()) {
                if (virtualPlayer.won().not()) {
                    virtualPlayer.play()
                    if (virtualPlayer.won()) {
                        virtualPlayer.toScore()
                    }
                }
            }
            fills()
        }
    }

    private fun notGameOver(row: Int, col: Int): Boolean {
        val isEmptyPosition = Board.cells[row][col].isEmpty()
        val someOneWon = humanPlayer.won() || virtualPlayer.won()
        return isEmptyPosition && someOneWon.not()
    }

    private fun newGame() {
        board.restart()
        virtualPlayer.randomPlay()
        fills()
    }

    private fun fills() {
        points.text = getString(R.string.score, humanPlayer.points, virtualPlayer.points)
        board.run { row, col ->
            when(board.cells[row][col].content) {
                Seed.O -> print(row, col, R.color.lightBlue, "X")
                Seed.X -> print(row, col, R.color.lightPink, "0")
                else   -> print(row, col, R.color.colorGreen, null)
            }
        }
    }

    private fun print(row: Int, col: Int, colorId: Int, symbol: String?) {
        val color = ResourcesCompat.getColor(resources, colorId, null)
        val button = buttons.find { it.tag == "$row:$col" }
        button?.let {
            it.setBackgroundColor(color)
            it.text = symbol
        }
    }
}
