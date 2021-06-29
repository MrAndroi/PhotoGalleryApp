package com.yarmouk.photogalleryapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


//Data that we will receive for each image from the api
@Parcelize
data class UnSplashImage(
    val id: String,
    val urls: UnsplashPhotoUrls,
    val user: UnsplashUser,
    val alt_description:String,
    val likes:Int,
    val width:Int,
    val height:Int,
): Parcelable {


    @Parcelize
    data class UnsplashPhotoUrls(
        val regular: String,
        val small: String,
        val full:String,
    ):Parcelable

    @Parcelize
    data class UnsplashUser(
        val name: String,
        val username: String
    ):Parcelable
}
