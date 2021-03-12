package io.github.leoallvez.tictactoe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.res.ResourcesCompat
import io.github.leoallvez.tictactoe.model.Board
import io.github.leoallvez.tictactoe.model.HumanPlayer
import io.github.leoallvez.tictactoe.model.VirtualPlayer
import kotlinx.android.synthetic.main.fragment_one_player.*

class OnePlayerFragment : Fragment() {

    private val humanPlayer = HumanPlayer(Seed.O)
    private val virtualPlayer = VirtualPlayer(Seed.X)
    private val board = Board
    private val buttons = mutableListOf<Button>()
//    private val buttons: List<Button> by lazy {
//        listOf(
//            btnRow0Col0, btnRow0Col1, btnRow0Col2,
//            btnRow1Col0, btnRow1Col1, btnRow1Col2,
//            btnRow2Col0, btnRow2Col1, btnRow2Col2
//        )
//    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one_player, container, false)
    }

    private fun startElements(view: View) {

//        buttons.forEach {
//            val positions = it.tag.toString().split(":")
//            val row = positions[0].toInt()
//            val col = positions[1].toInt()
//            it.setOnClickListener { played(row, col) }
//        }

        bReset.setOnClickListener {
            startGame()
        }

        startGame()
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

    fun newGame(view: View) {
        startGame()
    }

    private fun notGameOver(row: Int, col: Int): Boolean {
        val isEmptyPosition = Board.cells[row][col].isEmpty()
        val someOneWon = humanPlayer.won() || virtualPlayer.won()
        return isEmptyPosition && someOneWon.not()
    }

    private fun startGame() {
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
