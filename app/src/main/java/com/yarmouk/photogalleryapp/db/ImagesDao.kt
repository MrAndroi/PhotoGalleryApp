package com.yarmouk.photogalleryapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.yarmouk.photogalleryapp.models.UnSplashImage
import com.yarmouk.photogalleryapp.others.Constants.IMAGES_TABLE_NAME

//Data Access Object interface, this interface used by room library
//for defining your operations on the database table
@Dao
interface ImagesDao {

    //insert new image to database table
    //onConflict -> REPLACE means whenever we have two images with the same data then replace the image
    //and no redundant in data
    @Insert(onConflict = REPLACE)
    suspend fun insertImage(image:UnSplashImage)

    //delete image from database
    @Delete
    suspend fun deleteImage(image:UnSplashImage)

    //get all images from database as livedata of images list
    @Query("SELECT * FROM $IMAGES_TABLE_NAME")
    fun getAllImages():LiveData<List<UnSplashImage>>

    //function to check if image is saved passing image id and checking if our database has this id or not
    //if it has -> return image id
    //else -> return null
    @Query("SELECT id FROM $IMAGES_TABLE_NAME WHERE :imageId==id")
    suspend fun checkIfImageSaved(imageId:String):String?

}