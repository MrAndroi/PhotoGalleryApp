package com.yarmouk.photogalleryapp.api

import com.yarmouk.photogalleryapp.models.UnSplashResponse
import com.yarmouk.photogalleryapp.others.Constants.CLIENT_ID
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnSplashApi {

    //function to search for image by key word, ex:Cat,Dogs
    @Headers("Accept-Version: v1", "Authorization: Client-ID $CLIENT_ID")
    @GET("search/photos")
    suspend fun searchForImages(
        //Keyword to search for
        @Query("query") query: String,
        //Number of page to query for(for pagination)
        @Query("page") page: Int,
        //number of images per page(for pagination)
        @Query("per_page") perPage: Int
    ):UnSplashResponse
}