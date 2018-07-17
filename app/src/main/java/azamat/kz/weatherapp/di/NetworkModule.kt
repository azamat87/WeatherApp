package pro.wipon.com.wipon.di.modules

import azamat.kz.weatherapp.MAX_CONNECTION_TIMEOUT
import azamat.kz.weatherapp.MAX_READ_TIMEOUT
import azamat.kz.weatherapp.MAX_WRITE_TIMEOUT
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Asus on 10.05.2018.
 */
@Module
class NetworkModule {
    val BASE_URL = "https://maps.googleapis.com/"
    @Provides
    @Singleton
    fun provideRetrofit(builder: Retrofit.Builder): Retrofit {
        return builder.baseUrl(BASE_URL)
                .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitBuilder(client: OkHttpClient, converter: Gson): Retrofit.Builder {
        return Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(converter))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
    }

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
                .connectTimeout(MAX_CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(MAX_READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(MAX_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @Singleton
    fun provideConverter(): Gson {
        return GsonBuilder()
                .create()
    }


}