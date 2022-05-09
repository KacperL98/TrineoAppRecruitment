package kacper.litwinow.trineoapprecruitment.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kacper.litwinow.trineoapprecruitment.common.*
import kacper.litwinow.trineoapprecruitment.login.repository.LoginRepository
import kacper.litwinow.trineoapprecruitment.model.AuthenticationResponse
import kacper.litwinow.trineoapprecruitment.model.Credentials
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _result = MutableLiveData<LoginActionResult>()
    val result: LiveData<LoginActionResult> = _result

    fun login(credentials: Credentials) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val authenticateResponse = loginRepository.authenticate(
                    username = credentials.username,
                    password = credentials.password
                )

                if (authenticateResponse.isSuccessful) {
                    authorize(authenticateResponse.body())
                } else {
                    _result.postValue(AuthenticateError)
                }

            } catch (e: Exception) {
                _result.postValue(Error)
            }
        }
    }

    private suspend fun authorize(authenticationResponse: AuthenticationResponse?) {
        withContext(Dispatchers.IO) {
            try {
                val authorizeResponse = authenticationResponse?.let {
                    loginRepository.authorize(it)
                }

                if (authorizeResponse?.isSuccessful == true) {
                    _result.postValue(LoginSuccess)
                } else {
                    _result.postValue(AuthorizeError)
                }

            } catch (e: Exception) {
                _result.postValue(Error)
            }
        }
    }
}