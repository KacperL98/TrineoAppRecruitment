package kacper.litwinow.trineoapprecruitment.login.repository

import kacper.litwinow.trineoapprecruitment.api.CredentialsApi
import kacper.litwinow.trineoapprecruitment.login.repository.LoginRepository
import kacper.litwinow.trineoapprecruitment.model.AuthenticationResponse
import retrofit2.Response
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val credentialsApi: CredentialsApi
) : LoginRepository {

    override suspend fun authenticate(
        username: String,
        password: String
    ): Response<AuthenticationResponse> {
        return credentialsApi.authenticate(username, password)
    }

    override suspend fun authorize(authenticationResponse: AuthenticationResponse): Response<Any> {
        return credentialsApi.authorize(authenticationResponse)
    }
}