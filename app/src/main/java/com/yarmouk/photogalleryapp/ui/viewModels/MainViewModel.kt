package com.yarmouk.photogalleryapp.ui.viewModels


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.yarmouk.photogalleryapp.models.UnSplashImage
import com.yarmouk.photogalleryapp.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val repo:Repository): ViewModel() {

    //query livedata for image searching initially it has random keyword
    private val query = MutableLiveData("android")

    //live data for holding saved images in the data base
    val savedImages = repo.getAllImages()
    //live data to check if weather image is saved or not(true -> saved, false -> not saved)
    val saveState = MutableLiveData(false)

    //this value holds our images list as liveData(paging data) and whenever we change
    //the query from fragment this list gets updated by help of switchMap
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

    //function to insert new image to the database
    fun insertImage(image:UnSplashImage) = viewModelScope.launch {
        repo.insertImage(image)
    }

    //function to delete image from the database
    fun deleteImage(image:UnSplashImage) = viewModelScope.launch {
        repo.deleteImage(image)
    }

    //function for updating savedState live data
    fun checkIfImageSaved(imageId: String){
        viewModelScope.launch {
            //from repository call checkIfImageSaved which will
            //check if the image id that we passed is inside the database
            //and if its we will got image id back
            //if its not we will get null
            //finally save this result inside variable
            val result = repo.checkIfImageSaved(imageId)

            //here we check if result value is null then the image is not saved
            //and we pass false to saveState live data
            if(result == null){
                saveState.postValue(false)
            }
            //if its not null then our image is already saved
            //and we pass true to saveState live data
            else{
                saveState.postValue(true)
            }
        }
    }

}