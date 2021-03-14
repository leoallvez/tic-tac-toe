package io.github.leoallvez.tictactoe.di

import io.github.leoallvez.tictactoe.GameRepository
import io.github.leoallvez.tictactoe.IGameRepository
import io.github.leoallvez.tictactoe.OnePlayerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidModule = module {
    single { this }
    single {
        GameRepository() as IGameRepository
    }
    viewModel {
        OnePlayerViewModel(repository = get())
    }
}