package azamat.kz.weatherapp.ui

import android.support.v7.util.DiffUtil
import azamat.kz.weatherapp.local.entity.Weather

/**
 * Created by Asus on 17.07.2018.
 */
class WeatherDiffCallback(private val newList: ArrayList<Weather>, private val oldList: ArrayList<Weather>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].temp == newList[newItemPosition].temp
    }
}