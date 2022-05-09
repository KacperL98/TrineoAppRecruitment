package kacper.litwinow.trineoapprecruitment.cookie

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

private const val COOKIE_RECEIVE = "Set-Cookie"

class ReceiveCookiesInterceptor @Inject constructor(
    private val cookiesPreferences: CookiesPreferences
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val response = chain.proceed(chain.request())
        val headers = response.headers(COOKIE_RECEIVE)
        val cookies = cookiesPreferences.receiveCookies()

        if (headers.isNotEmpty()) {
            headers.forEach {
                cookies.add(it)
            }
            cookiesPreferences.addCookies(cookies)
        }

        return response
    }
}