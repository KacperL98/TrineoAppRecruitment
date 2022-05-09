package kacper.litwinow.trineoapprecruitment.cameras.activity

import android.view.LayoutInflater
import androidx.activity.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kacper.litwinow.trineoapprecruitment.R
import kacper.litwinow.trineoapprecruitment.base.BaseActivity
import kacper.litwinow.trineoapprecruitment.cameras.fragment.CamerasFragment
import kacper.litwinow.trineoapprecruitment.cameras.viewmodel.CamerasViewModel
import kacper.litwinow.trineoapprecruitment.common.restartApplication
import kacper.litwinow.trineoapprecruitment.databinding.ActivityCamerasBinding

@AndroidEntryPoint
class CamerasActivity : BaseActivity<ActivityCamerasBinding>() {

    private val camerasViewModel: CamerasViewModel by viewModels()

    override val bindingInflater: (LayoutInflater) -> ActivityCamerasBinding
        get() = ActivityCamerasBinding::inflate

    override fun setUp() {
        replaceFragment(CamerasFragment())

        camerasViewModel.restart.observe(this) {
            it?.let {
                restartApplication()
            }
        }
    }

    override fun onBackPressed() {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.alert_title)
            .setIcon(R.drawable.alert_icon)
            .setMessage(getString(R.string.logout_message))
            .setPositiveButton(R.string.logout_info_dialog) { _, _ -> camerasViewModel.logout() }
            .setNegativeButton(R.string.cancel_dialog) { _, _ -> }
            .create()
            .show()
    }
}
