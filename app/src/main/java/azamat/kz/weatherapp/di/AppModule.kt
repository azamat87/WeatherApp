package pro.wipon.com.wipon.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Asus on 10.05.2018.
 */
@Module
class AppModule(val application: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context = application

}