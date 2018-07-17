package azamat.kz.weatherapp.mvp.view

import azamat.kz.weatherapp.local.entity.Weather
import com.arellomobile.mvp.MvpView

/**
 * Created by Asus on 15.07.2018.
 */
interface IMainView: MvpView {

    fun setAutoComplete(list: ArrayList<String>)

    fun setWeather(list: ArrayList<Weather>)
}