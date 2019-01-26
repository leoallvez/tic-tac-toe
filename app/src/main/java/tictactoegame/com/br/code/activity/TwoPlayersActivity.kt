package tictactoegame.com.br.code.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import tictactoegame.com.br.code.R

class TwoPlayersActivity : AppCompatActivity(), View.OnClickListener {
    var PLAYER_X = true

    var TURN_COUNT = 0
    var boardStatus = Array(3) { IntArray(3) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bReset?.setOnClickListener(this)

        b00.setOnClickListener(this)
        b01.setOnClickListener(this)
        b02.setOnClickListener(this)

        b10.setOnClickListener(this)
        b11.setOnClickListener(this)
        b12.setOnClickListener(this)

        b20.setOnClickListener(this)
        b21.setOnClickListener(this)
        b22.setOnClickListener(this)

        val intent = intent

        val player_1 = intent.getStringExtra("PLAYER_1")
        val player_2 = intent.getStringExtra("PLAYER_2")

        initializeBoardStatus()
    }

    override fun onClick(view: View) {
        Log.d(TAG, "Inside onClick")

        var resetButtonPressed = false

        when (view.id) {
            R.id.b00 -> {
                if (PLAYER_X) {
                    b00?.text = "X"
                    boardStatus[0][0] = 1
                } else {
                    b00?.text = "0"
                    boardStatus[0][0] = 0
                }
                b00?.isEnabled = false
            }

            R.id.b01 -> {
                if (PLAYER_X) {
                    b01?.text = "X"
                    boardStatus[0][1] = 1
                } else {
                    b01?.text = "0"
                    boardStatus[0][1] = 0
                }
                b01?.isEnabled = false
            }

            R.id.b02 -> {
                if (PLAYER_X) {
                    b02?.text = "X"
                    boardStatus[0][2] = 1
                } else {
                    b02?.text = "0"
                    boardStatus[0][2] = 0
                }
                b02?.isEnabled = false
            }

            R.id.b10 -> {
                if (PLAYER_X) {
                    b10?.text = "X"
                    boardStatus[1][0] = 1
                } else {
                    b10?.text = "0"
                    boardStatus[1][0] = 0
                }
                b10?.isEnabled = false
            }

            R.id.b11 -> {
                if (PLAYER_X) {
                    b11?.text = "X"
                    boardStatus[1][1] = 1
                } else {
                    b11?.text = "0"
                    boardStatus[1][1] = 0
                }
                b11?.isEnabled = false
            }

            R.id.b12 -> {
                if (PLAYER_X) {
                    b12?.text = "X"
                    boardStatus[1][2] = 1
                } else {
                    b12?.text = "0"
                    boardStatus[1][2] = 0
                }
                b12?.isEnabled = false
            }

            R.id.b20 -> {
                if (PLAYER_X) {
                    b20?.text = "X"
                    boardStatus[2][0] = 1
                } else {
                    b20?.text = "0"
                    boardStatus[2][0] = 0
                }
                b20?.isEnabled = false
            }

            R.id.b21 -> {
                if (PLAYER_X) {
                    b21?.text = "X"
                    boardStatus[2][1] = 1
                } else {
                    b21?.text = "0"
                    boardStatus[2][1] = 0
                }
                b21?.isEnabled = false
            }

            R.id.b22 -> {
                if (PLAYER_X) {
                    b22?.text = "X"
                    boardStatus[2][2] = 1
                } else {
                    b22?.text = "0"
                    boardStatus[2][2] = 0
                }
                b22?.isEnabled = false
            }

            R.id.bReset -> resetButtonPressed = true

            else -> {
            }
        }

        if (resetButtonPressed) {
            resetBoard()
        } else {
            TURN_COUNT++
            PLAYER_X = !PLAYER_X

            if (PLAYER_X) {
                setInfo("Player X turn")
            } else {
                setInfo("Player 0 turn")
            }

            if (TURN_COUNT == 9) {
                result("Game Draw")
            }

            checkWinner()
        }
    }

    private fun checkWinner() {

        Log.d(TAG, "Inside checkWinner")

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
                    result("Player X winner\n" + (i + 1) + " column")
                    break
                } else if (boardStatus[0][i] == 0) {
                    result("Player 0 winner\n" + (i + 1) + " column")
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
        Log.d(TAG, "Inside enableAllBoxes")
        b00?.isEnabled = value
        b01?.isEnabled = value
        b02?.isEnabled = value

        b10?.isEnabled = value
        b11?.isEnabled = value
        b12?.isEnabled = value

        b20?.isEnabled = value
        b21?.isEnabled = value
        b22?.isEnabled = value
    }

    private fun result(winner: String) {
        Log.d(TAG, "Inside result")

        setInfo(winner)
        enableAllBoxes(false)
    }

    private fun resetBoard() {
        Log.d(TAG, "Inside resetBoard")
        b00.text = ""
        b01.text = ""
        b02.text = ""

        b10.text = ""
        b11.text = ""
        b12.text = ""

        b20.text = ""
        b21.text = ""
        b22.text = ""

        enableAllBoxes(true)

        PLAYER_X = true
        TURN_COUNT = 0

        initializeBoardStatus()

        setInfo("Start Again!!!")

        Toast.makeText(this, "Board Reset", Toast.LENGTH_SHORT).show()
    }

    private fun setInfo(text: String) {
        tvInfo.text = text
    }

    private fun initializeBoardStatus() {
        for (i in 0..2) {
            for (j in 0..2) {
                boardStatus[i][j] = -1
            }
        }

    }

    companion object {

        private val TAG = TwoPlayersActivity::class.java.simpleName
    }
}
