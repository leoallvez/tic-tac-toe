package io.github.leoallvez.tictactoe

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GameApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d("app", "start app")
    }

    override fun onTerminate() {
        super.onTerminate()
        Log.d("app", "terminate app")
    }
}
