package azamat.kz.weatherapp.mvp.presenter

import azamat.kz.weatherapp.App
import azamat.kz.weatherapp.Location
import azamat.kz.weatherapp.Response
import azamat.kz.weatherapp.WeatherPOJO
import azamat.kz.weatherapp.interactor.MainInteractor
import azamat.kz.weatherapp.local.entity.Weather
import azamat.kz.weatherapp.mvp.view.IMainView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import javax.inject.Inject

/**
 * Created by Asus on 15.07.2018.
 */
@InjectViewState
class MainPresenter : MvpPresenter<IMainView>() {

    @Inject
    lateinit var mInteractor: MainInteractor

    private val STATUS_OK = "OK"

    init {
        App.applicationComponent().inject(this)
    }

    fun getWeather(input: String) {
        mInteractor.getFromApi(input)
                .subscribe(this::handlerSuccess, this::handlerError)
    }

    fun getAutoComplete(input: String) {
        mInteractor.getAutoComplete(input)
                .subscribe(this::handlerAutoComplete, this::handlerError)
    }

    fun getFromCache() {
        val list = mInteractor.getFromCache()
        viewState.setWeather(list as ArrayList<Weather>)
    }

    private fun handlerAutoComplete(response: Response<ArrayList<Location>>) {
        if (response.status == STATUS_OK) {
            val list = ArrayList<String>()
            response.predictions.mapTo(list) { it.description }
            viewState.setAutoComplete(list)
        }
    }

    private fun handlerSuccess(response: Response<ArrayList<WeatherPOJO>>) {
        if (response.list.size != 0) {
            val list = ArrayList<Weather>()
            response.list.mapTo(list) { Weather(it.id, it.name, it.main.temp, it.sys.country,0) }
            viewState.setWeather(list)
        }
    }

    private fun handlerError(throwable: Throwable) {

    }

}