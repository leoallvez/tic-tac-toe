package io.github.leoallvez.tictactoe.oneplayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.leoallvez.tictactoe.R
import io.github.leoallvez.tictactoe.Seed
import io.github.leoallvez.tictactoe.databinding.FragmentOnePlayerBinding

@AndroidEntryPoint
class OnePlayerFragment : Fragment() {

    private val viewModel: OnePlayerViewModel by viewModels()
    private lateinit var binding: FragmentOnePlayerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnePlayerBinding.inflate(inflater, container, false)
        startElements()
        return binding.root
    }

    private fun startElements() {
        binding.viewModel = viewModel
        viewModel.startGame()
        viewModel.getBoard().observe(viewLifecycleOwner, { board ->
            board?.run { row, col ->
                when(board.cells[row][col].content) {
                    Seed.O -> print(row, col, R.color.lightBlue, "O")
                    Seed.X -> print(row, col, R.color.lightPink, "X")
                    else   -> print(row, col, R.color.colorGreen, null)
                }
            }
        })
        viewModel.getPoints().observe(viewLifecycleOwner, { points ->
            val human = points.first
            val machine = points.second
            binding.points.text = getString(R.string.score, human, machine)
        })
    }

    private fun print(row: Int, col: Int, colorId: Int, symbol: String?) {
        val color = ResourcesCompat.getColor(resources, colorId, null)
        val button = binding.root.findViewWithTag<Button>("$row:$col")
        button?.let {
            it.setBackgroundColor(color)
            it.text = symbol
        }
    }
}
