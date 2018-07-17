package azamat.kz.weatherapp.interactor

import azamat.kz.weatherapp.Location
import azamat.kz.weatherapp.Response
import azamat.kz.weatherapp.WeatherPOJO
import azamat.kz.weatherapp.local.entity.Weather
import io.reactivex.Observable

/**
 * Created by Asus on 15.07.2018.
 */
interface MainInteractor {

    fun getAutoComplete(input: String): Observable<Response<ArrayList<Location>>>

    fun getFromApi(input: String): Observable<Response<ArrayList<WeatherPOJO>>>

    fun getFromCache(): List<Weather>
}