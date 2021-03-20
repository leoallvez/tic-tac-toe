package io.github.leoallvez.tictactoe.oneplayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.leoallvez.tictactoe.LivePoints
import io.github.leoallvez.tictactoe.MutableLivePoints
import io.github.leoallvez.tictactoe.Seed
import io.github.leoallvez.tictactoe.model.Board
import io.github.leoallvez.tictactoe.model.HumanPlayer
import io.github.leoallvez.tictactoe.model.VirtualPlayer

class GameRepository : IGameRepository {

    private val humanPlayer by lazy { HumanPlayer(Seed.O) }
    private val virtualPlayer by lazy { VirtualPlayer(Seed.X) }

    private val points: MutableLivePoints = MutableLiveData(Pair(0, 0))
    override fun getPoints(): LivePoints = points

    private val board: MutableLiveData<Board> = MutableLiveData(Board)
    override fun getBoard(): LiveData<Board> = board

    //TODO: save game state;
    override fun play(row: Int, col: Int) {
        if (notGameOver(row, col)) {
            humanPlayer.play(row, col)
            if (nobodyWon()) {
                virtualPlayer.play()
            }
        }
        updateLiveData()
    }

    override fun startGame() {
        Board.restart()
        virtualPlayer.randomPlay()
        updateLiveData()
    }

    private fun notGameOver(row: Int, col: Int): Boolean {
        val isEmptyPosition = Board.cells[row][col].isEmpty()
        return isEmptyPosition && nobodyWon()
    }

    private fun nobodyWon(): Boolean  {
        return humanPlayer.won().not() && virtualPlayer.won().not()
    }

    private fun updateLiveData() {
        board.value = Board
        points.value = Pair(humanPlayer.points, virtualPlayer.points)
    }
}
