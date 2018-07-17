package azamat.kz.weatherapp.ui

import android.annotation.SuppressLint
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import azamat.kz.weatherapp.R
import azamat.kz.weatherapp.local.entity.Weather
import kotlinx.android.synthetic.main.item_weather.view.*



/**
 * Created by Asus on 16.07.2018.
 */
class WeatherRvAdapter: RecyclerView.Adapter<WeatherRvAdapter.WeatherVH>() {

    private val list = ArrayList<Weather>()

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: WeatherVH, position: Int) {
        val weather = list[position]
        holder.tvTemp.text = (weather.temp - 273.16).toInt().toString() + "°C"
        holder.tvCity.text = "${weather.name}, ${weather.country}"
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        return WeatherVH(view)
    }

    fun addItems(items: ArrayList<Weather>) {
        if (list.isEmpty()) {
            list.addAll(items)
        } else {
            val diffResult = DiffUtil.calculateDiff(WeatherDiffCallback(items, list))
            diffResult.dispatchUpdatesTo(this)
        }
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    inner class WeatherVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvTemp = itemView.tv_temp
        val tvCity = itemView.tv_city

    }

}