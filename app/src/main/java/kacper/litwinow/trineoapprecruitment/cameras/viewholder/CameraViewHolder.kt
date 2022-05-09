package kacper.litwinow.trineoapprecruitment.cameras.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kacper.litwinow.trineoapprecruitment.R
import kacper.litwinow.trineoapprecruitment.databinding.CameraItemBinding
import kacper.litwinow.trineoapprecruitment.model.Camera

typealias OnCameraClick = (Camera) -> Unit

class CameraViewHolder(private val binding: CameraItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(camera: Camera, onCameraClick: OnCameraClick) {
        with(binding) {
            cameraName.text = camera.toCameraInfo().name
            root.setOnClickListener {
                onCameraClick.invoke(camera)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): CameraViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.camera_item, parent, false)
            val binding = CameraItemBinding.bind(view)
            return CameraViewHolder(binding)
        }
    }
}