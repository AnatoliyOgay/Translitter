package kz.app.anatoliy.translitter

import android.app.Application
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("ConstantConditionIf")
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            if (BuildConfig.DEBUG)
                printLogger()
            modules(KoinModules.modules)
        }
    }
}
