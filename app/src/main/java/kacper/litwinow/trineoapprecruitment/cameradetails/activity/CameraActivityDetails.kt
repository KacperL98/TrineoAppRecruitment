package kacper.litwinow.trineoapprecruitment.cameradetails.activity

import android.view.LayoutInflater
import android.view.MenuItem
import dagger.hilt.android.AndroidEntryPoint
import kacper.litwinow.trineoapprecruitment.R
import kacper.litwinow.trineoapprecruitment.base.BaseActivity
import kacper.litwinow.trineoapprecruitment.cameradetails.fragment.CameraFragmentDetails
import kacper.litwinow.trineoapprecruitment.cameras.fragment.CAMERA_KEY
import kacper.litwinow.trineoapprecruitment.databinding.ActivityCameraDetailsBinding
import kacper.litwinow.trineoapprecruitment.model.CameraInfo

@AndroidEntryPoint
class CameraActivityDetails : BaseActivity<ActivityCameraDetailsBinding>() {

    private val cameraInfo by lazy {
        intent?.getSerializableExtra(CAMERA_KEY) as CameraInfo?
    }
    override val bindingInflater: (LayoutInflater) -> ActivityCameraDetailsBinding
        get() = ActivityCameraDetailsBinding::inflate

    override fun setUp() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        replaceFragment(CameraFragmentDetails.createInstance(cameraInfo))
    }

}
