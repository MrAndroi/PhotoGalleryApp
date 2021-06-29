package com.yarmouk.photogalleryapp.models

//Response we receive from api list of images
data class UnSplashResponse(
    val results: List<UnSplashImage>
)
