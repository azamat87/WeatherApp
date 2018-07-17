package azamat.kz.weatherapp.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import azamat.kz.weatherapp.local.entity.Weather

/**
 * Created by Asus on 15.07.2018.
 */
@Database(entities = arrayOf(Weather::class), version = 1, exportSchema = true)
abstract class WeatherDatabase: RoomDatabase(){
    abstract fun getWeatherDao(): WeatherDao
}