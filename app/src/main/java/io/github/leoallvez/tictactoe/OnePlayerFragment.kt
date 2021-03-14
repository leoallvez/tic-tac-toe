package io.github.leoallvez.tictactoe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import io.github.leoallvez.tictactoe.databinding.FragmentOnePlayerBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class OnePlayerFragment : Fragment() {

    private val viewModel: OnePlayerViewModel by viewModel()
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
                    Seed.O -> print(row, col, R.color.lightBlue, "X")
                    Seed.X -> print(row, col, R.color.lightPink, "0")
                    else   -> print(row, col, R.color.colorGreen, null)
                }
            }
        })
        viewModel.getPoints().observe(viewLifecycleOwner, { points ->
            points?.let {
                binding.points.text = points
            }
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
