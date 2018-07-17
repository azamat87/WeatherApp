package azamat.kz.weatherapp.network

import azamat.kz.weatherapp.Location
import azamat.kz.weatherapp.Response
import azamat.kz.weatherapp.WeatherPOJO
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.Url

/**
 * Created by Asus on 15.07.2018.
 */
interface NetworkApi {

    @GET("maps/api/place/autocomplete/json")
    fun requestAutoComplete(@QueryMap map: HashMap<String, String>): Observable<Response<ArrayList<Location>>>

    @GET()
    fun requestWeather(@Url url: String): Observable<Response<ArrayList<WeatherPOJO>>>

}