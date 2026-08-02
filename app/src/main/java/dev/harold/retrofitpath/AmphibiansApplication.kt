package dev.harold.retrofitpath

import android.app.Application
import dev.harold.retrofitpath.data.AppContainer
import dev.harold.retrofitpath.data.DefaultAppContainer

class AmphibiansApplication: Application() {
    lateinit var container: AppContainer
     override fun onCreate() {
        super.onCreate()
         container = DefaultAppContainer()
    }
}