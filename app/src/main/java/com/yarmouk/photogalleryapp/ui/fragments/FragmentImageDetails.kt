package com.yarmouk.photogalleryapp.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.yarmouk.photogalleryapp.R
import com.yarmouk.photogalleryapp.databinding.ImageDetailsFragmentBinding
import com.yarmouk.photogalleryapp.ui.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentImageDetails: Fragment(R.layout.image_details_fragment) {

    //bind views
    private var _binding:ImageDetailsFragmentBinding? = null
    private val binding:ImageDetailsFragmentBinding get() = _binding!!

    //get instance of mainViewModel
    private val mainViewModel:MainViewModel by viewModels()

    //get image data from arguments
    private val args:FragmentImageDetailsArgs by navArgs()
    private val image by lazy {
        args.image
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //check if the image saved passing image id which will update
        //save state live data in view model
        //true -> if the image is saved
        //false -> if the image is not saved
        mainViewModel.checkIfImageSaved(image.id)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ImageDetailsFragmentBinding.bind(view)

        setUpObservers()
        setUpViews()

    }

    private fun setUpObservers() {
        //observe on save state value inside mainViewModel and update save image based on the value
        mainViewModel.saveState.observe(viewLifecycleOwner){saved ->
            if(saved){
                //set bookmarked image for save button if the image in already saved
                binding.btnSaveImage.setImageResource(R.drawable.bookmarked)
            }
            else{
                //set bookmark image for save button if the image is not saved
                binding.btnSaveImage.setImageResource(R.drawable.bookmark)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setUpViews(){
        binding.apply {
            //go back
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            //load full image resolution with coil
            ivDetailsImage.load(args.image.urls.full){
                error(R.drawable.test)
                crossfade(true)
                crossfade(500)
                error(R.drawable.image_not_found)
                placeholder(R.drawable.loading)
            }

            //bind image data with the views
            tvDescription.text = image.alt_description
            tvUploadedBy.text = "Image uploaded by ${image.user.name}"
            tvLikedBy.text = "Liked by ${image.likes}"
            tvWidth.text = "Width ${image.width} px"
            tvHeight.text = "Height ${image.height} px"

            //button to insert image into room database
            btnSaveImage.setOnClickListener {
                //check if the image is already saved
                if(mainViewModel.saveState.value == true){
                    //if the image is already saved then delete image from database
                    mainViewModel.deleteImage(image)
                }
                else{
                    //else insert the image into database and show snackbar
                    mainViewModel.insertImage(image)

                    Snackbar.make(requireView(),"Image saved successfully",Snackbar.LENGTH_SHORT)
                        .setBackgroundTint(ContextCompat.getColor(requireContext(),R.color.primaryColor))
                        .show()
                }
                //update save state value
                mainViewModel.checkIfImageSaved(image.id)

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}