package azamat.kz.weatherapp.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import azamat.kz.weatherapp.local.entity.Weather

/**
 * Created by Asus on 15.07.2018.
 */
@Dao interface WeatherDao {

    @Insert
    fun insert(weather: Weather)

    // Удаление
    @Delete
    fun delete(weather: Weather)

    // Получение всех Schedule из бд
    @Query("DELETE FROM weather")
    fun deleteAll()

    // Получение всех Schedule из бд
    @Query("SELECT * FROM weather")
    fun getWeather(): List<Weather>


}