package net.kibotu.parallaxscrollingview.demo

import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import net.kibotu.logger.LogcatLogger
import net.kibotu.logger.Logger

class App : MultiDexApplication() {

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun onCreate() {
        super.onCreate()

        Logger.addLogger(LogcatLogger())
    }
}