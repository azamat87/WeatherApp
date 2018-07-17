package azamat.kz.weatherapp.di

import android.arch.persistence.room.Room
import android.content.Context
import azamat.kz.weatherapp.local.WeatherDao
import azamat.kz.weatherapp.local.WeatherDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by Asus on 15.07.2018.
 */
@Module
class RoomModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(context: Context): WeatherDatabase {
        return Room.databaseBuilder(context.applicationContext, WeatherDatabase::class.java, "db_weather_1").allowMainThreadQueries().build()
    }

    @Singleton
    @Provides
    fun providesProductDao(database: WeatherDatabase): WeatherDao {
        return database.getWeatherDao()
    }

}