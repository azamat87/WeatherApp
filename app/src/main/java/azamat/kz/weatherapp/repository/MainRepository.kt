package azamat.kz.weatherapp.repository

import azamat.kz.weatherapp.Location
import azamat.kz.weatherapp.Response
import azamat.kz.weatherapp.WeatherPOJO
import azamat.kz.weatherapp.local.entity.Weather
import io.reactivex.Observable

/**
 * Created by Asus on 15.07.2018.
 */
interface MainRepository {

    fun getQueryAutoComplete(input: String): Observable<Response<ArrayList<Location>>>

    fun getWeatherFromApi(input: String): Observable<Response<ArrayList<WeatherPOJO>>>

    fun getWeatherFromCache(): List<Weather>
}