package kacper.litwinow.trineoapprecruitment.common

import kacper.litwinow.trineoapprecruitment.model.Camera

sealed interface LoginActionResult
sealed interface CamerasActionResult

object Error : LoginActionResult, CamerasActionResult
object Loading : LoginActionResult, CamerasActionResult

data class CamerasSuccess(val camera: List<Camera>) : CamerasActionResult
object UnAuthorize : CamerasActionResult
object CamerasEmpty : CamerasActionResult

object LoginSuccess : LoginActionResult
object AuthorizeError : LoginActionResult
object AuthenticateError : LoginActionResult