package io.github.leoallvez.tictactoe.model

import io.github.leoallvez.tictactoe.Seed
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class VirtualPlayerTest {

    @Test
    fun `when seed is X opponentSeed is O`() {
        // given
        val player: VirtualPlayer = makePlayer(Seed.X)
        // when
        val result: Boolean = player.opponentSeed == Seed.O
        // then
        Assert.assertEquals(true, result)
    }

    @Test
    fun `when seed is O opponentSeed is X`() {
        // given
        val player: VirtualPlayer = makePlayer(Seed.O)
        // when
        val result: Boolean = player.opponentSeed == Seed.X
        // then
        Assert.assertEquals(true, result)
    }

    @Test
    fun `when board is full randomPlay() return false`() {
        // given
        val player: VirtualPlayer = makePlayer()
        // when
        every { player.board.cells } returns makeBoard(Seed.O)
        // then
        Assert.assertEquals(false, player.randomPlay())
    }

    @Test
    fun `when board is not full randomPlay() return true`() {
        // given
        val player: VirtualPlayer = makePlayer()
        // when
        every { player.board.cells } returns makeBoard()
        // then
        Assert.assertEquals(true, player.randomPlay())
    }

    @Test
    fun `when player are create the all board cells are empty`() {
        // give
        val player = VirtualPlayer(Seed.X)
        // when
        player.board.run { cells ->
            val emptyCells = cells.filter { it.isEmpty() }
            // then
            Assert.assertEquals(3, emptyCells.size)
        }
    }

    private fun makePlayer(seed: Seed = Seed.O) = VirtualPlayer(seed = seed, board = mockk())

    private fun makeBoard(seed: Seed? = null): Array<Array<Cell>> {
        return Array(Board.SIZE) { row ->
            Array(Board.SIZE) { column ->
                if (seed != null) {
                    Cell(row, column).apply {
                        content = seed
                    }
                } else {
                    Cell(row, column)
                }
            }
        }
    }
}
