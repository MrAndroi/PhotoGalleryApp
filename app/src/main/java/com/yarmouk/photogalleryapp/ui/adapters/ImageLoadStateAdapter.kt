package com.yarmouk.photogalleryapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yarmouk.photogalleryapp.databinding.ImageLoadStateFooterBinding

//Adapter to show the footer of the list while loading new results or if we got errors
class ImageLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<ImageLoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = ImageLoadStateFooterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return LoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class LoadStateViewHolder(private val binding: ImageLoadStateFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnRetryFooter.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                //show progress bar if the state is Loading
                progressBarFooter.isVisible = loadState is LoadState.Loading
                //show retry btn if the state is not Loading (error)
                btnRetryFooter.isVisible = loadState !is LoadState.Loading
                //show error text if the state is not Loading (error)
                tvFooterError.isVisible = loadState !is LoadState.Loading
                //show error icon if the state is not Loading (error)
                errorIconFooter.isVisible = loadState !is LoadState.Loading
            }
        }
    }
}