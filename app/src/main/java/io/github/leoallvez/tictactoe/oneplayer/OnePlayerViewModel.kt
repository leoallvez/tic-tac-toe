package io.github.leoallvez.tictactoe.oneplayer

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.github.leoallvez.tictactoe.LivePoints
import io.github.leoallvez.tictactoe.model.Board

class OnePlayerViewModel @ViewModelInject constructor(
        private val repository: IGameRepository
    ) : ViewModel() {

    fun getPoints(): LivePoints {
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
