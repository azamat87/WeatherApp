package azamat.kz.weatherapp

/**
 * Created by Asus on 15.07.2018.
 */

data class WeatherPOJO(val id: Int,
                       val name: String,
                       val main: Main,
                       val sys: Sys
)

data class Main(val temp: Double,
                val pressure: Double,
                val humidity: Double,
                val temp_min: Double,
                val temp_max: Double)

data class Sys(val country: String)

data class Response<T>(val predictions: T,
                       val list: T,
                       val status: String)

data class Location(val description: String)