package io.github.leoallvez.tictactoe

import io.github.leoallvez.tictactoe.model.Board
import io.github.leoallvez.tictactoe.model.VirtualPlayer
import org.junit.Assert
import org.junit.Test

class VirtualPlayerTest {

    @Test
    fun virtualPlayer_getOpponentSeedO() {
        val player = VirtualPlayer(Seed.X)
        Assert.assertEquals(Seed.O, player.opponentSeed)
    }

    @Test
    fun virtualPlayer_getOpponentSeedX() {
        val player = VirtualPlayer(Seed.O)
        Assert.assertEquals(Seed.X, player.opponentSeed)
    }

    @Test
    fun virtualPlayer_test() {
        val player = VirtualPlayer(Seed.O)
        val board = Board
        player.randomPlay()
        //board.cells.filter { it.any().not().e }
        //Assert.assertEquals()
    }
}