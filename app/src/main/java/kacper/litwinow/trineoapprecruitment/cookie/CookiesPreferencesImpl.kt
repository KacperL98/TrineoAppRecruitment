package kacper.litwinow.trineoapprecruitment.cookie

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

private const val KEY = "kacper.litwinow.trineoapprecruitment"
private const val COOKIE = "kacper.litwinow.trineoapprecruitment.cookie"


class CookiesPreferencesImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : CookiesPreferences {

    private val preferences: SharedPreferences
        get() = context.getSharedPreferences(KEY, Context.MODE_PRIVATE)

    override fun addCookies(cookies: HashSet<String>) {
        preferences.edit(commit = true) {

            putStringSet(COOKIE, cookies)
        }
    }

    override fun receiveCookies(): HashSet<String> {
        return preferences.getStringSet(COOKIE, emptySet())?.toHashSet() ?: hashSetOf()
    }
}