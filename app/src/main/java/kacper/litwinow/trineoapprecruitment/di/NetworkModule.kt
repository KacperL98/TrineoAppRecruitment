package kacper.litwinow.trineoapprecruitment.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kacper.litwinow.trineoapprecruitment.api.CameraApi
import kacper.litwinow.trineoapprecruitment.api.CredentialsApi
import kacper.litwinow.trineoapprecruitment.cameras.repository.CamerasRepository
import kacper.litwinow.trineoapprecruitment.cameras.repository.CamerasRepositoryImpl
import kacper.litwinow.trineoapprecruitment.cookie.AddCookiesInterceptor
import kacper.litwinow.trineoapprecruitment.cookie.CookiesPreferences
import kacper.litwinow.trineoapprecruitment.cookie.CookiesPreferencesImpl
import kacper.litwinow.trineoapprecruitment.cookie.ReceiveCookiesInterceptor
import kacper.litwinow.trineoapprecruitment.login.repository.LoginRepository
import kacper.litwinow.trineoapprecruitment.login.repository.LoginRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://login.eagleeyenetworks.com"

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideOkhttp(
        addCookiesInterceptor: AddCookiesInterceptor,
        receiveCookiesInterceptor: ReceiveCookiesInterceptor
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(addCookiesInterceptor)
            .addInterceptor(receiveCookiesInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofitCredentialsApi(retrofit: Retrofit): CredentialsApi {
        return retrofit.create(CredentialsApi::class.java)
    }

    @Provides
    fun provideRetrofitCameraApi(retrofit: Retrofit): CameraApi {
        return retrofit.create(CameraApi::class.java)
    }

    @Singleton
    @Provides
    fun provideLoginRepositoryImpl(credentialsApi: CredentialsApi): LoginRepository {
        return LoginRepositoryImpl(credentialsApi)
    }

    @Singleton
    @Provides
    fun provideCamerasRepositoryImpl(cameraApi: CameraApi): CamerasRepository {
        return CamerasRepositoryImpl(cameraApi)
    }

    @Singleton
    @Provides
    fun provideCookiesPreferencesImpl(@ApplicationContext context: Context): CookiesPreferences {
        return CookiesPreferencesImpl(context)
    }

    @Singleton
    @Provides
    fun provideReceiveCookiesInterceptorImpl(cookiesPreferences: CookiesPreferences): ReceiveCookiesInterceptor {
        return ReceiveCookiesInterceptor(cookiesPreferences)
    }

    @Singleton
    @Provides
    fun provideAddCookiesInterceptor(cookiesPreferences: CookiesPreferences): AddCookiesInterceptor {
        return AddCookiesInterceptor(cookiesPreferences)
    }
}