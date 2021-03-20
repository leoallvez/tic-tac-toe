package io.github.leoallvez.tictactoe.oneplayer

import androidx.lifecycle.LiveData
import io.github.leoallvez.tictactoe.model.Board

interface IGameRepository {

    fun getPoints(): LiveData<Pair<Int, Int>>

    fun getBoard(): LiveData<Board>

    fun play(row: Int, col: Int)

    fun startGame()
}