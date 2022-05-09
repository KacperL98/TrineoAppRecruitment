package kacper.litwinow.trineoapprecruitment.cameras.fragment

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kacper.litwinow.trineoapprecruitment.R
import kacper.litwinow.trineoapprecruitment.base.BaseFragment
import kacper.litwinow.trineoapprecruitment.cameradetails.activity.CameraActivityDetails
import kacper.litwinow.trineoapprecruitment.cameras.adapter.CamerasAdapter
import kacper.litwinow.trineoapprecruitment.cameras.viewmodel.CamerasViewModel
import kacper.litwinow.trineoapprecruitment.common.*
import kacper.litwinow.trineoapprecruitment.databinding.FragmentCamerasBinding

const val CAMERA_KEY = "kacper.litwinow.trineoapprecruitment.camera"

@AndroidEntryPoint
class CamerasFragment : BaseFragment<FragmentCamerasBinding>() {

    private val camerasViewModel: CamerasViewModel by viewModels()
    private val camerasAdapter by lazy {
        CamerasAdapter { camera ->
            val intent = Intent(requireContext(), CameraActivityDetails::class.java).apply {
                putExtra(CAMERA_KEY, camera.toCameraInfo())
            }
            requireActivity().startActivity(intent)
        }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCamerasBinding
        get() = FragmentCamerasBinding::inflate

    override fun setUp() {
        onClickToolbar()
        camerasViewModel.camerasList()
        binding.rvCameras.adapter = camerasAdapter

        camerasViewModel.result.observe(viewLifecycleOwner) { result ->
            when (result) {
                Error -> {
                    binding.progressBar.gone()
                    binding.errorUnexpected.show()
                }

                Loading -> {
                    binding.progressBar.show()
                    binding.errorUnexpected.gone()
                }

                is CamerasSuccess -> {
                    binding.progressBar.gone()
                    binding.errorUnexpected.gone()
                    camerasAdapter.submitList(result.camera)
                    requireContext().toastMessage(R.string.camera_list_downloaded)
                }

                CamerasEmpty -> {
                    binding.progressBar.gone()
                    binding.errorUnexpected.show()
                }

                UnAuthorize -> {
                    binding.progressBar.gone()
                    binding.errorUnexpected.show()
                }
            }
        }
    }

    private fun onClickToolbar() {
        binding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.logout_menu -> {
                    requireContext().restartApplication()
                    camerasViewModel.logout()
                    true
                }
                else -> false
            }
        }
    }
}