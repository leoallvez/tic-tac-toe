package io.github.leoallvez.tictactoe.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.github.leoallvez.tictactoe.oneplayer.GameRepository
import io.github.leoallvez.tictactoe.oneplayer.IGameRepository
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class AndroidModule {
    @Singleton
    @Binds
    abstract fun bindGameRepository(
            gameRepository: GameRepository
    ): IGameRepository
}
