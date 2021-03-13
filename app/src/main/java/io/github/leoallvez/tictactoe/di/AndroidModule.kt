package io.github.leoallvez.tictactoe.di

import io.github.leoallvez.tictactoe.OnePlayerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidModule = module {
    single { this }
    viewModel {
        OnePlayerViewModel()
    }
}