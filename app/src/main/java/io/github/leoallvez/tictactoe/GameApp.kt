package io.github.leoallvez.tictactoe

import android.app.Application
import io.github.leoallvez.tictactoe.di.androidModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class GameApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(androidContext = this@GameApp)
            modules(androidModule)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}
