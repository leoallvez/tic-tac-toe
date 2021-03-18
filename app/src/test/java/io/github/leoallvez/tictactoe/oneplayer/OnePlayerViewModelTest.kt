package io.github.leoallvez.tictactoe.oneplayer

import io.mockk.mockk
import junit.framework.TestCase
import org.junit.Test

class OnePlayerViewModelTest {

    val repositoty: IGameRepository = mockk<GameRepository>()
    val viewModel: OnePlayerViewModel = OnePlayerViewModel(repositoty)

    @Test
    fun testGetPoints() {}
    @Test
    fun testGetBoard() {}
}