package io.github.leoallvez.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.leoallvez.tictactoe.model.Cell

typealias LivePoints = LiveData<Pair<Int, Int>>
typealias MutableLivePoints = MutableLiveData<Pair<Int, Int>>
typealias GetPositionFunction = () -> List<List<Cell>>