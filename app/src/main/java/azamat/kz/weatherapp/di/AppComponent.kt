package azamat.kz.weatherapp.di

import azamat.kz.weatherapp.mvp.presenter.MainPresenter
import dagger.Component
import pro.wipon.com.wipon.di.modules.ApiModule
import pro.wipon.com.wipon.di.modules.AppModule
import javax.inject.Singleton

/**
 * Created by Asus on 15.07.2018.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, ApiModule::class, RoomModule::class,MainModule::class))
interface AppComponent {

//    fun plus(mainModule: MainModule): MainComponent
    fun inject(presenter: MainPresenter)
}