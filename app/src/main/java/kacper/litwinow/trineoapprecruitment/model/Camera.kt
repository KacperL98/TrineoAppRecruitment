package kacper.litwinow.trineoapprecruitment.model

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

    fun getName() = this[NAME_INDEX] as String?
    fun getGuid() = this[GUID_INDEX] as String?
    fun getSerialNumber() = this[SERIAL_NUMBER_INDEX] as String?
    fun getTimezone() = this[TIMEZONE_INDEX] as String?
    fun getIp() = this[IP_ADDRESS_INDEX] as String?
    fun getVideoStatus() = this[VIDEO_STATUS_INDEX] as String?

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
    fun getDeviceStatus(): String {
        return when (statusCamera?.countOneBits()) {
            0 -> "No Change"
            8 -> "Password protected"
            18 -> "Streaming"
            20 -> "Registered"
            17 -> "Camera On"
            else -> "Offline"
        }
    }
}

