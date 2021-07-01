package com.yarmouk.photogalleryapp.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.yarmouk.photogalleryapp.models.UnSplashImage

//type converter class used by room in order to save complex data in our database
class Converters {

    //this function convert UnSplashUser Object to string with help of Gson library
    @TypeConverter
    fun fromUser(user: UnSplashImage.UnsplashUser):String{
        return Gson().toJson(user)
    }

    //this function convert string Object to UnSplashUser with help of Gson library
    @TypeConverter
    fun fromStringToUser(userString:String):UnSplashImage.UnsplashUser{
        return Gson().fromJson(userString,UnSplashImage.UnsplashUser::class.java)
    }

    //this function convert UnsplashPhotoUrls Object to string with help of Gson library
    @TypeConverter
    fun fromUrls(urls:UnSplashImage.UnsplashPhotoUrls):String{
        return Gson().toJson(urls)
    }

    //this function convert string Object to UnsplashPhotoUrls with help of Gson library
    @TypeConverter
    fun fromStringToUrls(urlsString:String):UnSplashImage.UnsplashPhotoUrls{
        return Gson().fromJson(urlsString,UnSplashImage.UnsplashPhotoUrls::class.java)
    }

}