package com.yarmouk.photogalleryapp.api

import com.yarmouk.photogalleryapp.models.UnSplashResponse
import com.yarmouk.photogalleryapp.others.Constants.CLIENT_ID
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnSplashApi {

    @Headers("Accept-Version: v1", "Authorization: Client-ID $CLIENT_ID")
    @GET("search/photos")
    suspend fun searchForImages(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ):UnSplashResponse
}