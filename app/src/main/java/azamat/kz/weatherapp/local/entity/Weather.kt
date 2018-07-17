package azamat.kz.weatherapp.local.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Asus on 15.07.2018.
 */
@Entity
data class Weather(@PrimaryKey @ColumnInfo(name = "id")val id: Int,
                   @ColumnInfo(name = "name")val name: String,
                   @ColumnInfo(name = "temp") val temp: Double,
                   @ColumnInfo(name = "country") val country: String,
                   @ColumnInfo(name = "time") val time: Long)