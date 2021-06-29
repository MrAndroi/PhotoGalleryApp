package com.yarmouk.photogalleryapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.yarmouk.photogalleryapp.R
import com.yarmouk.photogalleryapp.databinding.ImageDetailsFragmentBinding

class FragmentImageDetails: Fragment(R.layout.image_details_fragment) {

    private var _binding:ImageDetailsFragmentBinding? = null
    private val binding:ImageDetailsFragmentBinding get() = _binding!!

    private val args:FragmentImageDetailsArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ImageDetailsFragmentBinding.bind(view)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}