package io.github.leoallvez.tictactoe

import androidx.lifecycle.LiveData
import io.github.leoallvez.tictactoe.model.Board

interface IGameRepository {

    fun getPoints(): LiveData<String>

    fun getBoard(): LiveData<Board>

    fun play(row: Int, col: Int)

    fun startGame()
}