package kacper.litwinow.trineoapprecruitment.cookie

interface CookiesPreferences {
    fun addCookies(cookies: HashSet<String>)
    fun receiveCookies(): HashSet<String>
}