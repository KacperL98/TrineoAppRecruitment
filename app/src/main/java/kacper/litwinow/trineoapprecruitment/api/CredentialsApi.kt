package kacper.litwinow.trineoapprecruitment.api

import kacper.litwinow.trineoapprecruitment.model.AuthenticationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface CredentialsApi {

    @POST("/g/aaa/authenticate")
    suspend fun authenticate(
        @Query("username") username: String,
        @Query("password") password: String
    ): Response<AuthenticationResponse>

    @POST("/g/aaa/authorize")
    suspend fun authorize(
        @Body authenticationResponse: AuthenticationResponse
    ): Response<Any>
}