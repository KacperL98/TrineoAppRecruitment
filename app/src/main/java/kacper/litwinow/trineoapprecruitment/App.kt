package kacper.litwinow.trineoapprecruitment

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kacper.litwinow.trineoapprecruitment.cookie.CookiesPreferences
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {
    @Inject
    lateinit var cookiesPreferences: CookiesPreferences
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}