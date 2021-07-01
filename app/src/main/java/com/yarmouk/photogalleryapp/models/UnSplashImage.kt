package com.yarmouk.photogalleryapp.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.yarmouk.photogalleryapp.others.Constants.IMAGES_TABLE_NAME
import kotlinx.android.parcel.Parcelize


//Data that we will receive for each image from the api
@Parcelize
@Entity(tableName = IMAGES_TABLE_NAME)
data class UnSplashImage(
    @PrimaryKey
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
