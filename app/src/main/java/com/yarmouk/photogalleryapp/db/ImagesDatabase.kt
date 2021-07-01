package com.yarmouk.photogalleryapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yarmouk.photogalleryapp.models.UnSplashImage

//Database abstract class, which is used by room to create instance of
//our database and Dao, we pass entities in @Database annotation
// which defines what objects we will store in our database
//and @TypeConverters annotation tells room how to store complex data objects
@Database(entities = [UnSplashImage::class],version = 1)
@TypeConverters(Converters::class)
abstract class ImagesDatabase :RoomDatabase(){

    abstract fun getImagesDao():ImagesDao

}