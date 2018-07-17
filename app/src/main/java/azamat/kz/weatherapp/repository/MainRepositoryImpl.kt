package azamat.kz.weatherapp.repository

import azamat.kz.weatherapp.Location
import azamat.kz.weatherapp.Response
import azamat.kz.weatherapp.WeatherPOJO
import azamat.kz.weatherapp.local.WeatherDao
import azamat.kz.weatherapp.local.entity.Weather
import azamat.kz.weatherapp.network.NetworkApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject


/**
 * Created by Asus on 15.07.2018.
 */
class MainRepositoryImpl @Inject constructor(private val api: NetworkApi, val local: WeatherDao) : MainRepository {

    override fun getWeatherFromApi(input: String): Observable<Response<ArrayList<WeatherPOJO>>> {
        val url = "https://api.openweathermap.org/data/2.5/find?q=$input&appid=dc2aaaabfb8534eabbf93140ccf15f5c"
        return api.requestWeather(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::setToCache)
    }

    override fun getQueryAutoComplete(input: String): Observable<Response<ArrayList<Location>>> {
        val map = HashMap<String, String>()
        map.put("input", input)
        map.put("types", "(cities)")
        map.put("key", "AIzaSyDru-YUEjxozsrXCqrr-yZZmGUFz0kptJ4")
        return api.requestAutoComplete(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getWeatherFromCache(): List<Weather> {
        val list = local.getWeather()
        val arrList = ArrayList<Weather>()
        if (list.isNotEmpty()) {
            for (weather in list) {
                val hour = hoursAgo(weather.time)
                if (hour > 1) {
                    local.delete(weather)
                } else {
                    arrList.add(weather)
                }
            }
        }
        return arrList
    }

    private fun setToCache(response: Response<ArrayList<WeatherPOJO>>) {
        if (response.list.size != 0) {
            local.deleteAll()
            for (weatherPOJO in response.list) {
                local.insert(Weather(weatherPOJO.id,
                        weatherPOJO.name,
                        weatherPOJO.main.temp,
                        weatherPOJO.sys.country,
                        System.currentTimeMillis()))
            }
        }
    }

    private fun hoursAgo(date: Long): Int {
        val now = Calendar.getInstance().time
        val differenceInMillis = now.time - date
        val differenceInHours = differenceInMillis / 1000L / 60L / 60L
        return differenceInHours.toInt()
    }
}