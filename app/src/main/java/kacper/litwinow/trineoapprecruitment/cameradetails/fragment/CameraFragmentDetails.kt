package kacper.litwinow.trineoapprecruitment.cameradetails.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import dagger.hilt.android.AndroidEntryPoint
import kacper.litwinow.trineoapprecruitment.base.BaseFragment
import kacper.litwinow.trineoapprecruitment.cameras.fragment.CAMERA_KEY
import kacper.litwinow.trineoapprecruitment.databinding.FragmentCameraDetailsBinding
import kacper.litwinow.trineoapprecruitment.model.CameraInfo

@AndroidEntryPoint
class CameraFragmentDetails : BaseFragment<FragmentCameraDetailsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCameraDetailsBinding
        get() = FragmentCameraDetailsBinding::inflate

    private val cameraInfo by lazy {
        arguments?.getSerializable(CAMERA_KEY) as CameraInfo?
    }

    override fun setUp() {
        with(binding) {
            cameraName.text = cameraInfo?.name
            ipCamera.text = cameraInfo?.ip
            timezoneCamera.text = cameraInfo?.timezone
            serialNumberCamera.text = cameraInfo?.serial_number
            guidCamera.text = cameraInfo?.guid
            cameraStatus.text = cameraInfo?.getDeviceStatus().toString()
            onBackAction()
        }
    }

    private fun onBackAction() {
        binding.toolbarBackArrow.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    companion object {
        fun createInstance(cameraInfo: CameraInfo?): CameraFragmentDetails {
            return CameraFragmentDetails().apply {
                arguments = bundleOf(CAMERA_KEY to cameraInfo)
            }
        }
    }
}