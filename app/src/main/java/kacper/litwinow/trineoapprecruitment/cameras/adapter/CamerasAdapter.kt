package kacper.litwinow.trineoapprecruitment.cameras.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kacper.litwinow.trineoapprecruitment.cameras.viewholder.CameraViewHolder
import kacper.litwinow.trineoapprecruitment.cameras.viewholder.OnCameraClick
import kacper.litwinow.trineoapprecruitment.databinding.CameraItemBinding
import kacper.litwinow.trineoapprecruitment.model.Camera

class CamerasAdapter(private val onCameraClick: OnCameraClick) :
    ListAdapter<Camera, CameraViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CameraViewHolder {
        val binding =
            CameraItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CameraViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CameraViewHolder, position: Int) {
        holder.bind(getItem(position), onCameraClick)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Camera>() {

            override fun areItemsTheSame(oldItem: Camera, newItem: Camera): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Camera, newItem: Camera): Boolean =
                oldItem == newItem
        }
    }
}
