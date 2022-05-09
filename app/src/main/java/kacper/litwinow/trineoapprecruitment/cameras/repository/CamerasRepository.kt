package kacper.litwinow.trineoapprecruitment.cameras.repository

import kacper.litwinow.trineoapprecruitment.model.Camera

interface CamerasRepository {
    suspend fun getCameras(): List<Camera>
}