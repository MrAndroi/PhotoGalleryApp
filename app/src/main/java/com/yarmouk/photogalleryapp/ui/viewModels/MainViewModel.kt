package com.yarmouk.photogalleryapp.ui.viewModels


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.yarmouk.photogalleryapp.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val repo:Repository): ViewModel() {

    private val query = MutableLiveData("android")

    //this value holds our images list as liveData(paging data) and when ever we change
    //the query from fragment this list gets updated by help or switchMap
    val images = query.switchMap { queryString ->
        //check if queryString is empty and search for random Keyword
        if(queryString.isEmpty()){
            repo.getImagesResults("cats").cachedIn(viewModelScope)
        }
        else {
            repo.getImagesResults(queryString).cachedIn(viewModelScope)
        }
    }

    //this function used to update our query from ui
    fun searchImages(newQuery: String) {
        query.value = newQuery
    }

}