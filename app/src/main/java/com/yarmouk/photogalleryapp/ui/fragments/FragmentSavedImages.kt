package com.yarmouk.photogalleryapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yarmouk.photogalleryapp.R
import com.yarmouk.photogalleryapp.databinding.SavedImagesFragmentBinding
import com.yarmouk.photogalleryapp.ui.adapters.SavedImagesAdapter
import com.yarmouk.photogalleryapp.ui.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

//fragment to get saved images from database and display it in recyclerview
@AndroidEntryPoint
class FragmentSavedImages:Fragment(R.layout.saved_images_fragment) {

    //bind the views
    private var _binding:SavedImagesFragmentBinding? = null
    private val binding:SavedImagesFragmentBinding get() = _binding!!

    //create saved images adapter
    private lateinit var imagesAdapter: SavedImagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        imagesAdapter = SavedImagesAdapter {image ->
            //click callback to navigate to details fragment
            val direction = FragmentSavedImagesDirections
                .actionFragmentSavedImagesToFragmentImageDetails(image)

            findNavController().navigate(direction)
        }
    }

    //instance of mainViewModel
    private val mainViewModel:MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = SavedImagesFragmentBinding.bind(view)

        setUpObservers()
        setUpSavedImagesRecyclerView()
    }

    //set up our recyclerview with adapter
    private fun setUpSavedImagesRecyclerView(){
        binding.rvSavedImages.setHasFixedSize(true)
        binding.rvSavedImages.adapter = imagesAdapter
    }

    private fun setUpObservers() {
        //observe on savedImages inside the database
        mainViewModel.savedImages.observe(viewLifecycleOwner){
            imagesAdapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}