package io.github.leoallvez.tictactoe.oneplayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.leoallvez.tictactoe.Seed
import io.github.leoallvez.tictactoe.model.Board
import io.github.leoallvez.tictactoe.model.HumanPlayer
import io.github.leoallvez.tictactoe.model.VirtualPlayer

class GameRepository : IGameRepository {

    private val humanPlayer = HumanPlayer(Seed.O)
    private val virtualPlayer = VirtualPlayer(Seed.X)
    //TODO put the points in a pair;
    private val points: MutableLiveData<String> = MutableLiveData(
            "Me: 0, Machine: 0"
    )

    override fun getPoints(): LiveData<String> = points

    private val board: MutableLiveData<Board> = MutableLiveData(Board)
    override fun getBoard(): LiveData<Board> = board

    //TODO: save game state;
    override fun play(row: Int, col: Int) {
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

    override fun startGame() {
        Board.restart()
        virtualPlayer.randomPlay()
        updateLiveData()
    }

    private fun notGameOver(row: Int, col: Int): Boolean {
        val isEmptyPosition = Board.cells[row][col].isEmpty()
        val someOneWon = humanPlayer.won() || virtualPlayer.won()
        return isEmptyPosition && someOneWon.not()
    }

    private fun updateLiveData() {
        board.value = Board
        points.value = "Me: ${humanPlayer.points}, Machine: ${virtualPlayer.points}"
    }
}
