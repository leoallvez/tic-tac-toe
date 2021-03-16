package io.github.leoallvez.tictactoe

import io.github.leoallvez.tictactoe.model.Cell
import org.junit.Test

import org.junit.Assert.*

class CellTest {

    @Test
    fun cell_isEmpty() {
        val cell = Cell(row = 0, col = 0).apply { content = Seed.EMPTY }
        assertEquals(true, cell.isEmpty())
    }

    @Test
    fun cell_isNotEmpty() {
        val cell = Cell(row = 0, col = 0).apply { content = Seed.X }
        assertEquals(false, cell.isEmpty())
    }

    @Test
    fun cell_isNotCenter() {
        val cell = Cell(row = 0, col = 0)
        assertEquals(true, cell.isNotCenter())
    }

    @Test
    fun cell_isCenter() {
        val cell = Cell(row = 1, col = 1)
        assertEquals(false, cell.isNotCenter())
    }

    @Test
    fun cell_cleaned() {
        val cell = Cell(row = 0, col = 0).apply { content = Seed.X }
        cell.clear()
        assertEquals( Seed.EMPTY, cell.content)
    }
}
