package com.yarmouk.photogalleryapp.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.yarmouk.photogalleryapp.api.PagingSource
import com.yarmouk.photogalleryapp.api.UnSplashApi
import javax.inject.Inject

class Repository @Inject constructor(private val unsplashApi:UnSplashApi){

    //this function will get pagination data from our paging source and return it as liveData
    fun getImagesResults(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PagingSource(unsplashApi, query) }
        ).liveData

}