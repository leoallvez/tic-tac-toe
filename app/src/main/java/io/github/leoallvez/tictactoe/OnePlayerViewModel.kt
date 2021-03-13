package io.github.leoallvez.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.leoallvez.tictactoe.model.Board
import io.github.leoallvez.tictactoe.model.HumanPlayer
import io.github.leoallvez.tictactoe.model.VirtualPlayer

class OnePlayerViewModel : ViewModel() {

    private val humanPlayer = HumanPlayer(Seed.O)
    private val virtualPlayer = VirtualPlayer(Seed.X)
    private val points: MutableLiveData<String> = MutableLiveData(
        "Me: 0, Machine: 0"
    )
    fun getPoints(): LiveData<String> = points
    private val _board = Board
    private val board: MutableLiveData<Board> = MutableLiveData(_board)
    fun getBoard(): LiveData<Board> = board

    private fun updateLiveData() {
        board.value = _board
        points.value = "Me: ${humanPlayer.points}, Machine: ${virtualPlayer.points}"
    }

    fun play(row: Int, col: Int) {
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
            updateLiveData()
        }
    }

    private fun notGameOver(row: Int, col: Int): Boolean {
        val isEmptyPosition = _board.cells[row][col].isEmpty()
        val someOneWon = humanPlayer.won() || virtualPlayer.won()
        return isEmptyPosition && someOneWon.not()
    }

    fun startGame() {
        _board.restart()
        virtualPlayer.randomPlay()
        updateLiveData()
    }
}