package azamat.kz.weatherapp.di

import azamat.kz.weatherapp.interactor.MainInteractor
import azamat.kz.weatherapp.interactor.MainInteractorImpl
import azamat.kz.weatherapp.local.WeatherDao
import azamat.kz.weatherapp.network.NetworkApi
import azamat.kz.weatherapp.repository.MainRepository
import azamat.kz.weatherapp.repository.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Asus on 15.07.2018.
 */
@Module
class MainModule {

    @Provides
    @Singleton
    fun provideInteractor(repository: MainRepository): MainInteractor {
        return MainInteractorImpl(repository)
    }

    @Provides
    @Singleton
    fun provideRepository(api: NetworkApi, local: WeatherDao): MainRepository {
        return MainRepositoryImpl(api, local)
    }
}