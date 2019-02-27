package tictactoegame.com.br.code.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import tictactoegame.com.br.code.R

class TwoPlayersActivity : AppCompatActivity(), View.OnClickListener {
    var playerX = true
    var turnCount = 0
    var boardStatus = Array(3) { IntArray(3) }

    val buttons: Array<Button> = arrayOf (btn00, btn01, btn02, btn03, btn04, btn05, btn06, btn07, btn08)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnReset?.setOnClickListener(this)

        for (btn in buttons) {
            btn.setOnClickListener(this)
        }

        initializeBoardStatus()
    }

    private fun setButton(line: Int, column: Int, btn: Button) {
        if (playerX) {
            btn.text = "X"
            boardStatus[line][column] = 1
        } else {
            btn.text = "0"
            boardStatus[line][column] = 0
        }
        btn.isEnabled = false
    }

    //TODO: Parei nessa refatoração;
    override fun onClick(view: View) {

        var resetButtonPressed = false

        when (view.id) {
            R.id.btn00 -> setButton(0, 0, btn00)
            R.id.btn01 -> setButton(0, 1, btn01)
            R.id.btn02 -> setButton(0, 2, btn02)

            R.id.btn03 -> setButton(1, 0, btn03)
            R.id.btn04 -> setButton(1, 1, btn04)
            R.id.btn05 -> setButton(1, 2, btn05)

            R.id.btn06 -> setButton(2, 0, btn06)
            R.id.btn07 -> setButton(2, 1, btn07)
            R.id.btn08 -> setButton(2, 2, btn08)

            R.id.btnReset -> resetButtonPressed = true
        }

        if (resetButtonPressed) {
            resetBoard()
        } else {
            turnCount++
            playerX = !playerX

            if (playerX) {
                setInfo("Player X turn")
            } else {
                setInfo("Player 0 turn")
            }

            if (turnCount == 9) {
                result("Game Draw")
            }

            checkWinner()
        }
    }

    private fun checkWinner() {

        Log.d(tag, "Inside checkWinner")

        //Horizontal --- rows
        for (i in 0..2) {
            if (boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2]) {
                if (boardStatus[i][0] == 1) {
                    result("Player X winner\n" + (i + 1) + " row")
                    break
                } else if (boardStatus[i][0] == 0) {
                    result("Player 0 winner\n" + (i + 1) + " row")
                    break
                }
            }
        }

        //Vertical --- columns
        for (i in 0..2) {
            if (boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]) {
                if (boardStatus[0][i] == 1) {
                    result("Player X winner\n ${(i + 1)} column")
                    break
                } else if (boardStatus[0][i] == 0) {
                    result("Player 0 winner\n ${(i + 1)} column")
                    break
                }
            }
        }

        //First diagonal
        if (boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]) {
            if (boardStatus[0][0] == 1) {
                result("Player X winner\nFirst Diagonal")
            } else if (boardStatus[0][0] == 0) {
                result("Player 0 winner\nFirst Diagonal")
            }
        }

        //Second diagonal
        if (boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0]) {
            if (boardStatus[0][2] == 1) {
                result("Player X winner\nSecond Diagonal")
            } else if (boardStatus[0][2] == 0) {
                result("Player 0 winner\nSecond Diagonal")
            }
        }
    }


    private fun enableAllBoxes(value: Boolean) {

        for(btn in buttons) {
            btn.isEnabled = value
        }
    }

    private fun result(winner: String) {
        setInfo(winner)
        enableAllBoxes(false)
    }

    private fun resetBoard() {

        for(btn in buttons) {
            btn.text = ""
        }

        enableAllBoxes(true)

        playerX = true
        turnCount = 0

        initializeBoardStatus()

        setInfo("Start Again!!!")

        Toast.makeText(this, "Board Reset", Toast.LENGTH_SHORT).show()
    }

    private fun setInfo(text: String) {
        //tvInfo.text = text
    }

    private fun initializeBoardStatus() {
        for (i in 0..2) {
            for (j in 0..2) {
                boardStatus[i][j] = -1
            }
        }
    }

    companion object {
        private val tag = TwoPlayersActivity::class.java.simpleName
    }
}
