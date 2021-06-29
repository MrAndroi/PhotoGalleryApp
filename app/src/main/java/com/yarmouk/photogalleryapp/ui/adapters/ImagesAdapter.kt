package com.yarmouk.photogalleryapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.yarmouk.photogalleryapp.R
import com.yarmouk.photogalleryapp.databinding.ImageItemBinding
import com.yarmouk.photogalleryapp.models.UnSplashImage

class ImagesAdapter:PagingDataAdapter<UnSplashImage,ImagesAdapter.ImagesViewHolder>(IMAGE_COMPARATOR){

    class ImagesViewHolder(private val binding:ImageItemBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(photo: UnSplashImage) {
            binding.apply {
                image.load(photo.urls.regular){
                    crossfade(true)
                    crossfade(500)
                    error(R.drawable.test)
                }
                tvUserName.text = photo.user.name
            }
        }
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val binding =
            ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ImagesViewHolder(binding)
    }

    companion object {
        private val IMAGE_COMPARATOR = object : DiffUtil.ItemCallback<UnSplashImage>() {
            override fun areItemsTheSame(oldItem: UnSplashImage, newItem: UnSplashImage) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: UnSplashImage, newItem: UnSplashImage) =
                oldItem == newItem
        }
    }
}

