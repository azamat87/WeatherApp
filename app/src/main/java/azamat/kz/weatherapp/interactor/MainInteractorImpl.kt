package azamat.kz.weatherapp.interactor

import azamat.kz.weatherapp.Location
import azamat.kz.weatherapp.Response
import azamat.kz.weatherapp.WeatherPOJO
import azamat.kz.weatherapp.local.entity.Weather
import azamat.kz.weatherapp.repository.MainRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Asus on 15.07.2018.
 */
class MainInteractorImpl @Inject constructor(private val repository: MainRepository) : MainInteractor {

    override fun getFromCache(): List<Weather> {
        return repository.getWeatherFromCache()
    }

    override fun getFromApi(input: String): Observable<Response<ArrayList<WeatherPOJO>>> {
        return repository.getWeatherFromApi(input)
    }


    override fun getAutoComplete(input: String): Observable<Response<ArrayList<Location>>> {
        return repository.getQueryAutoComplete(input)
    }
}