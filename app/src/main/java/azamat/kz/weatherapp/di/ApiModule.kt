package pro.wipon.com.wipon.di.modules

import azamat.kz.weatherapp.network.NetworkApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by Asus on 10.05.2018.
 */
@Module(includes = arrayOf(NetworkModule::class))
class ApiModule {

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): NetworkApi{
        return retrofit.create(NetworkApi::class.java)
    }
}