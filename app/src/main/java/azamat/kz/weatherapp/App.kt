package azamat.kz.weatherapp

import android.app.Application
import azamat.kz.weatherapp.di.AppComponent
import azamat.kz.weatherapp.di.DaggerAppComponent
import pro.wipon.com.wipon.di.modules.AppModule

/**
 * Created by Asus on 15.07.2018.
 */
class App: Application() {

    companion object {
        @JvmStatic lateinit var graph: AppComponent

        fun applicationComponent() = graph
    }

    override fun onCreate() {
        super.onCreate()
        graph = DaggerAppComponent.builder()
                .appModule(AppModule(applicationContext))
                .build()

    }

}