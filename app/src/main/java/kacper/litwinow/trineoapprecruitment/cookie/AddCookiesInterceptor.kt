package kacper.litwinow.trineoapprecruitment.cookie

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AddCookiesInterceptor @Inject constructor(
    private val cookiesPreferences: CookiesPreferences
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        val cookies = cookiesPreferences.receiveCookies()

        cookies.forEach {
            builder.addHeader("Cookie", it)
        }
        return chain.proceed(builder.build())
    }
}