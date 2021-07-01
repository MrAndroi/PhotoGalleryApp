package com.yarmouk.photogalleryapp.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.yarmouk.photogalleryapp.api.PagingSource
import com.yarmouk.photogalleryapp.api.UnSplashApi
import com.yarmouk.photogalleryapp.db.ImagesDao
import com.yarmouk.photogalleryapp.models.UnSplashImage
import javax.inject.Inject

class Repository @Inject constructor(private val unsplashApi:UnSplashApi,private val imagesDao: ImagesDao){

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


    //function to insert new images to database
    suspend fun insertImage(image: UnSplashImage) = imagesDao.insertImage(image)

    //function to delete image from database
    suspend fun deleteImage(image: UnSplashImage) = imagesDao.deleteImage(image)

    //function to check if image is saved passing image id and checking if our database has this id or not
    //if it has -> return image id
    //else -> return null
    suspend fun checkIfImageSaved(imageId: String) = imagesDao.checkIfImageSaved(imageId)

    //function to get all saved images from database
    fun getAllImages() = imagesDao.getAllImages()

}