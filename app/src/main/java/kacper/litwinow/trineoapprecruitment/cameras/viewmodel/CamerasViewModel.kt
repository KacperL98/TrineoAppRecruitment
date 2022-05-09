package kacper.litwinow.trineoapprecruitment.cameras.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kacper.litwinow.trineoapprecruitment.cameras.repository.CamerasRepository
import kacper.litwinow.trineoapprecruitment.common.CamerasActionResult
import kacper.litwinow.trineoapprecruitment.common.CamerasSuccess
import kacper.litwinow.trineoapprecruitment.common.Error
import kacper.litwinow.trineoapprecruitment.cookie.CookiesPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CamerasViewModel @Inject constructor(
    private val camerasRepository: CamerasRepository,
    private val cookiesPreferences: CookiesPreferences
) : ViewModel() {

    private val _resultCameras = MutableLiveData<CamerasActionResult>()
    val result: LiveData<CamerasActionResult> = _resultCameras

    private val _restart = MutableLiveData<Unit>()
    val restart: LiveData<Unit> = _restart

    fun camerasList() {
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val response = camerasRepository.getCameras()
                _resultCameras.postValue(CamerasSuccess(response))

            } catch (e: Exception) {
                _resultCameras.postValue(Error)
            }
        }
    }

    fun logout() {
        _restart.postValue(Unit)
        cookiesPreferences.addCookies(hashSetOf())
    }
}
