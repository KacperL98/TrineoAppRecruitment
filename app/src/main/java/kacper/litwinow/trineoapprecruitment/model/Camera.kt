package kacper.litwinow.trineoapprecruitment.model

import androidx.annotation.StringRes
import kacper.litwinow.trineoapprecruitment.R
import java.io.Serializable

private const val NAME_INDEX = 2
private const val GUID_INDEX = 8
private const val SERIAL_NUMBER_INDEX = 9
private const val DEVICE_STATUS_INDEX = 10
private const val TIMEZONE_INDEX = 11
private const val IP_ADDRESS_INDEX = 14
private const val VIDEO_STATUS_INDEX = 19

class Camera : ArrayList<Any?>(), Serializable {
    fun toCameraInfo() =
        CameraInfo(
            getName(),
            getGuid(),
            getSerialNumber(),
            getTimezone(),
            getIp(),
            getVideoStatus(),
            statusCamera()
        )

    private fun getName() = this[NAME_INDEX] as String?
    private fun getGuid() = this[GUID_INDEX] as String?
    private fun getSerialNumber() = this[SERIAL_NUMBER_INDEX] as String?
    private fun getTimezone() = this[TIMEZONE_INDEX] as String?
    private fun getIp() = this[IP_ADDRESS_INDEX] as String?
    private fun getVideoStatus() = this[VIDEO_STATUS_INDEX] as String?

    private fun statusCamera() = (this[DEVICE_STATUS_INDEX] as Double?)?.toInt()
}

data class CameraInfo(
    val name: String?,
    val guid: String?,
    val serial_number: String?,
    val timezone: String?,
    val ip: String?,
    val video_status: String?,
    val statusCamera: Int?,
) : Serializable {
    //TODO add correct strings
    @StringRes
    fun getDeviceStatus() : Int {
        return when (statusCamera?.countOneBits()) {
            0 -> R.string.no_change_camera
            8 -> R.string.password_protected_camera
            18 -> R.string.streaming_camera
            20 -> R.string.registered_camera
            17 -> R.string.camera_on_camera
            else -> R.string.offline_camera
        }
    }
}

