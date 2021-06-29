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
import com.yarmouk.photogalleryapp.others.Constants.FOOTER_VIEW_TYPE
import com.yarmouk.photogalleryapp.others.Constants.IMAGE_VIEW_TYPE

//Images RecyclerView adapter
class ImagesAdapter(val onClick:(image:UnSplashImage) -> Unit):PagingDataAdapter<UnSplashImage,ImagesAdapter.ImagesViewHolder>(IMAGE_COMPARATOR){

    inner class ImagesViewHolder(private val binding:ImageItemBinding) :RecyclerView.ViewHolder(binding.root){
        //Bind the data

        fun bind(image: UnSplashImage) {
            binding.apply {
                //pass click function with image object to navigate to the details of the image
                binding.root.setOnClickListener{onClick(image)}

                //load image url using coil library
                this.image.load(image.urls.regular){
                    crossfade(true)
                    crossfade(500)
                    error(R.drawable.test)
                }
                //show the name of user who upload the image
                tvUserName.text = image.user.name
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

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount){
            IMAGE_VIEW_TYPE
        }else {
            FOOTER_VIEW_TYPE
        }
    }

    //Callback for calculating the diff between two non-null items in the list
    companion object {
        private val IMAGE_COMPARATOR = object : DiffUtil.ItemCallback<UnSplashImage>() {
            override fun areItemsTheSame(oldItem: UnSplashImage, newItem: UnSplashImage) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: UnSplashImage, newItem: UnSplashImage) =
                oldItem == newItem
        }
    }
}

