package com.yarmouk.photogalleryapp.models

data class UnSplashImage(
    val id: String,
    val urls: UnsplashPhotoUrls,
    val user: UnsplashUser,
    val alt_description:String,
    val likes:Int,
    val width:Int,
    val height:Int,
){

    data class UnsplashPhotoUrls(
        val regular: String,
        val small: String,
        val full:String,
    )

    data class UnsplashUser(
        val name: String,
        val username: String
    )
}
