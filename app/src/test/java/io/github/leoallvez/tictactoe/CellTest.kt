package io.github.leoallvez.tictactoe

import io.github.leoallvez.tictactoe.model.Cell
import org.junit.Test
import org.junit.Assert.*

class CellTest {

    @Test
    fun `when content is EMPTY isEmpty() return true`() {
        // given
        val cell = makeCell()
        // when
        cell.content = Seed.EMPTY
        val cellIsEmpty: Boolean = cell.isEmpty()
        // then
        assertEquals(true, cellIsEmpty)
    }

    @Test
    fun `when content is not EMPTY isEmpty() return false`() {
        // given
        val cell = makeCell()
        // when
        cell.content = Seed.X
        val cellIsEmpty: Boolean = cell.isEmpty()
        // then
        assertEquals(false, cellIsEmpty)
    }

    @Test
    fun `when row and col is not 1 isNotCenter() return true`() {
        // given
        val cell = makeCell()
        // when
        val isNotCenter: Boolean = cell.isNotCenter()
        // then
        assertEquals(true, isNotCenter)
    }

    @Test
    fun `when row and col is 1 isNotCenter() return false`() {
        // given
        val cell = makeCell(row = 1, col = 1)
        // when
        val isNotCenter: Boolean = cell.isNotCenter()
        // then
        assertEquals(false, isNotCenter)
    }

    @Test
    fun `when clear() is called content is cleaned`() {
        // given
        val cell = makeCell()
        // when
        cell.content = Seed.X
        cell.clear()
        val cellIsEmpty: Boolean = cell.content == Seed.EMPTY
        // then
        assertEquals(true, cellIsEmpty)
    }

    private fun makeCell(row: Int = 0, col: Int = 0): Cell = Cell(row, col)
}
