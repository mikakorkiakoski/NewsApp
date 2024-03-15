package fi.mobiilikehitysprojektir13.newsapp

import android.app.Application
import fi.mobiilikehitysprojektir13.newsapp.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(networkModule)
        }
    }
}