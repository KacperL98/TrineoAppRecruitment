package kacper.litwinow.trineoapprecruitment.login.repository

import kacper.litwinow.trineoapprecruitment.model.AuthenticationResponse
import retrofit2.Response

interface LoginRepository {
    suspend fun authenticate(username: String, password: String): Response<AuthenticationResponse>
    suspend fun authorize(authenticationResponse: AuthenticationResponse): Response<Any>
}