package com.yarmouk.photogalleryapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.yarmouk.photogalleryapp.R
import com.yarmouk.photogalleryapp.databinding.SearchImageFragmentBinding
import com.yarmouk.photogalleryapp.others.Constants.FOOTER_VIEW_TYPE
import com.yarmouk.photogalleryapp.others.onSubmit
import com.yarmouk.photogalleryapp.ui.adapters.ImageLoadStateAdapter
import com.yarmouk.photogalleryapp.ui.adapters.ImagesAdapter
import com.yarmouk.photogalleryapp.ui.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSearchImage: Fragment(R.layout.search_image_fragment) {

    //bind search image layout
    private  var _binding:SearchImageFragmentBinding? = null
    private  val binding: SearchImageFragmentBinding
    get() = _binding!!
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var imagesAdapter: ImagesAdapter
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gridLayoutManager = GridLayoutManager(requireContext(),2)
        linearLayoutManager = LinearLayoutManager(requireContext())

        imagesAdapter = ImagesAdapter {image ->
            val direction = FragmentSearchImageDirections
                .actionFragmentSearchImageToFragmentImageDetails(image)
            findNavController().navigate(direction)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = SearchImageFragmentBinding.bind(view)

        setUpObservers()
        setUpImagesAdapter()
        setUpImagesRecyclerView()
        binding.apply {
            etSearch.onSubmit {
                //call search image function from view model passing the queryString from edit text
                mainViewModel.searchImages(it)
            }
            btnRetryImages.setOnClickListener {
                imagesAdapter.retry()
            }
            listAndGridSwitcher.setOnCheckedChangeListener { _, isChecked ->
                //remove old layoutManager
                rvImages.layoutManager = null

                //check if the switch is checked then make it grid
                if(isChecked){
                    rvImages.layoutManager = gridLayoutManager
                }
                //otherwise make it list
                else{
                    rvImages.layoutManager = linearLayoutManager
                }
            }
        }

    }

    private fun setUpImagesRecyclerView() {
        //this listener used to change the snap size of our grid
        //because when we want to show the footer span size should be 1
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                val viewType = imagesAdapter.getItemViewType(position)
                return if(viewType == FOOTER_VIEW_TYPE) 1
                else 2
            }

        }
        binding.rvImages.setHasFixedSize(false)
        binding.rvImages.layoutManager = gridLayoutManager
        //set recyclerview adapter with footer
        binding.rvImages.adapter = imagesAdapter.withLoadStateFooter(
            footer = ImageLoadStateAdapter{imagesAdapter.retry()}
        )
    }

    private fun setUpImagesAdapter() {
        //this will listen to the state of our adapter
        imagesAdapter.addLoadStateListener { state ->
            //when ever the state become loading show progress bar
            binding.progressBarImages.isVisible = state.source.refresh is LoadState.Loading
            //when ever the state is not loading show our recyclerview
            binding.rvImages.isVisible = state.source.refresh is LoadState.NotLoading


            //check if theres is network error or no results found
            if(state.source.refresh is LoadState.NotLoading &&
                state.append.endOfPaginationReached && imagesAdapter.itemCount < 1){

                //show error text and try again button and hide recyclerview
                binding.tvImagesError.isVisible = true
                binding.btnRetryImages.isVisible = true
                binding.rvImages.isVisible = false
            }
            else{

                binding.tvImagesError.isVisible = false
                binding.btnRetryImages.isVisible = false
                binding.rvImages.isVisible = true
            }
        }
    }

    private fun setUpObservers(){
        //observe images list and give the data to our adapter
        mainViewModel.images.observe(viewLifecycleOwner){
            imagesAdapter.submitData(viewLifecycleOwner.lifecycle,it)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvImages.layoutManager = null
        _binding = null
    }
}