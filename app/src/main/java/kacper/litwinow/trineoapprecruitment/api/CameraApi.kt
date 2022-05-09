package kacper.litwinow.trineoapprecruitment.api

import kacper.litwinow.trineoapprecruitment.model.Camera
import retrofit2.Response
import retrofit2.http.GET

interface CameraApi {

    @GET("g/device/list")
    suspend fun getCameras(): Response<List<Camera>>
}