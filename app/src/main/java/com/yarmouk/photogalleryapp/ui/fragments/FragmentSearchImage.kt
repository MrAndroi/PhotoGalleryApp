package com.yarmouk.photogalleryapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.yarmouk.photogalleryapp.R
import com.yarmouk.photogalleryapp.databinding.SearchImageFragmentBinding

class FragmentSearchImage: Fragment(R.layout.search_image_fragment) {

    private lateinit var binding:SearchImageFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SearchImageFragmentBinding.bind(view)

    }
}