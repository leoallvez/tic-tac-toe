package io.github.leoallvez.tictactoe.model

import io.github.leoallvez.tictactoe.Seed
import org.junit.Assert
import org.junit.Test

class HumanPlayerTest {

    @Test
    fun `when cell empty play() method change the board cell content`() {
        // given
        val player = HumanPlayer(Seed.X)
        // when
        val row = 0; val col = 0
        player.play(row, col)
        val cell = player.board.cells[row][col]
        // then
        Assert.assertEquals(Seed.X , cell.content)
    }

    @Test
    fun `when cell not empty play() method not change the board cell content`() {
        // given
        val player = HumanPlayer(Seed.X)
        // when
        val row = 0; val col = 0
        val cell = player.board.cells[row][col].apply {
            content = Seed.O
        }
        player.play(row, col)
        // then
        Assert.assertEquals(Seed.O, cell.content)
    }

    @Test
    fun `when toScore() is called points are increased`() {
        // given
        val player = HumanPlayer(Seed.X)
        // when
        player.toScore()
        // then
        Assert.assertEquals(1, player.points)
    }
}