package io.github.leoallvez.tictactoe.oneplayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.github.leoallvez.tictactoe.model.Board

class OnePlayerViewModel(private val repository: IGameRepository) : ViewModel() {

    fun getPoints(): LiveData<String> {
        return repository.getPoints()
    }

    fun getBoard(): LiveData<Board> {
        return repository.getBoard()
    }

    fun play(row: Int, col: Int) {
        repository.play(row, col)
    }

    fun startGame() {
        repository.startGame()
    }
}
