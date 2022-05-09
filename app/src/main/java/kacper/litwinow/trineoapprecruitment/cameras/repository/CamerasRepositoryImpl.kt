package kacper.litwinow.trineoapprecruitment.cameras.repository

import kacper.litwinow.trineoapprecruitment.api.CameraApi
import kacper.litwinow.trineoapprecruitment.model.Camera
import javax.inject.Inject

class CamerasRepositoryImpl @Inject constructor(
    private val cameraApi: CameraApi
) : CamerasRepository {
    override suspend fun getCameras(): List<Camera> {
        return try {
            cameraApi.getCameras().body()!!
        } catch (e: Exception) {
            emptyList()
        }
    }
}